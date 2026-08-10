/*
 * Copyright (c) 2018, Loong Wan (https://github.com/loong10k).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pac4j.core.ext.authentication;

import java.util.Optional;

import org.pac4j.core.client.IndirectClient;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.CallContext;
import org.pac4j.core.credentials.Credentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.http.FoundAction;
import org.pac4j.core.exception.http.HttpAction;
import org.pac4j.core.exception.http.StatusAction;
import org.pac4j.core.ext.Pac4jExtConstants;
import org.pac4j.core.ext.credentials.extractor.UsernamePasswordCaptchaCredentialsExtractor;
import org.pac4j.core.profile.creator.ProfileCreator;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.core.util.Pac4jConstants;
import org.pac4j.core.util.HttpActionHelper;

/**
 * TODO
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UsernamePasswordCaptchaFormClient extends IndirectClient {

    private String loginUrl;

    public final static String ERROR_PARAMETER = "error";

    public final static String MISSING_FIELD_ERROR = "missing_field";

    private String usernameParameter = Pac4jConstants.USERNAME;

    private String passwordParameter = Pac4jConstants.PASSWORD;

    private String captchaParameter = Pac4jExtConstants.CAPTCHA;


    private boolean postOnly = true;


    public UsernamePasswordCaptchaFormClient() {
    }

    public UsernamePasswordCaptchaFormClient(final String loginUrl, final Authenticator usernamePasswordAuthenticator) {
        this.loginUrl = loginUrl;
        setAuthenticatorIfUndefined(usernamePasswordAuthenticator);
    }

    public UsernamePasswordCaptchaFormClient(final String loginUrl, final String usernameParameter,
            final String passwordParameter, final String captchaParameter, boolean postOnly,
                      final Authenticator usernamePasswordAuthenticator) {
        this.loginUrl = loginUrl;
        this.usernameParameter = usernameParameter;
        this.passwordParameter = passwordParameter;
        this.captchaParameter = captchaParameter;
        this.postOnly = postOnly;
        setAuthenticatorIfUndefined(usernamePasswordAuthenticator);
    }

    public UsernamePasswordCaptchaFormClient(final String loginUrl, final Authenticator usernamePasswordAuthenticator,
                      final ProfileCreator profileCreator) {
        this.loginUrl = loginUrl;
        setAuthenticatorIfUndefined(usernamePasswordAuthenticator);
        setProfileCreatorIfUndefined(profileCreator);
    }

    @Override
    protected void internalInit(boolean forceReinit) {
        CommonHelper.assertNotBlank("loginUrl", this.loginUrl);
        CommonHelper.assertNotBlank("usernameParameter", this.usernameParameter);
        CommonHelper.assertNotBlank("passwordParameter", this.passwordParameter);
        CommonHelper.assertNotBlank("captchaParameter", this.captchaParameter);


        setRedirectionActionBuilderIfUndefined(callContext -> {
            final String finalLoginUrl = getUrlResolver().compute(this.loginUrl, callContext.webContext());
            return Optional.of(new FoundAction(finalLoginUrl));
        });


        setCredentialsExtractorIfUndefined(new UsernamePasswordCaptchaCredentialsExtractor(usernameParameter, passwordParameter, captchaParameter, postOnly));
    }

    @Override
    public Optional<Credentials> getCredentials(final CallContext callContext) {
        init();
        CommonHelper.assertNotNull("credentialsExtractor", getCredentialsExtractor());
        WebContext context = callContext.webContext();

        final String username = context.getRequestParameter(this.usernameParameter).orElse(null);
        Optional<Credentials> credentials;
        try {
            credentials = getCredentialsExtractor().extract(callContext);
            logger.debug("usernamePasswordCredentials: {}", credentials);
            if (credentials.isEmpty()) {
                throw handleInvalidCredentials(callContext, username, "Username and password cannot be blank -> return to the form with error",
                    MISSING_FIELD_ERROR);
            }
        } catch (final CredentialsException e) {
            throw handleInvalidCredentials(callContext, username, "Credentials validation fails -> return to the form with error",
                computeErrorMessage(e));
        }
        return credentials;
    }

    @Override
    protected Optional<Credentials> internalValidateCredentials(final CallContext callContext, final Credentials credentials) {
        CommonHelper.assertNotNull("authenticator", getAuthenticator());
        String username = credentials instanceof org.pac4j.core.ext.credentials.UsernamePasswordCaptchaCredentials
                ? ((org.pac4j.core.ext.credentials.UsernamePasswordCaptchaCredentials) credentials).getUsername() : null;
        try {
            return getAuthenticator().validate(callContext, credentials);
        } catch (final CredentialsException e) {
            throw handleInvalidCredentials(callContext, username,
                    "Credentials validation fails -> return to the form with error", computeErrorMessage(e));
        }
    }

    protected HttpAction handleInvalidCredentials(final CallContext callContext, final String username, String message, String errorMessage) {
        WebContext context = callContext.webContext();
        // it's an AJAX request -> unauthorized (instead of a redirection)
        if (getAjaxRequestResolver().isAjax(callContext)) {
            logger.info("AJAX request detected -> returning 401");
            return HttpActionHelper.buildUnauthenticatedAction(context);
        } else {
            String redirectionUrl = CommonHelper.addParameter(this.loginUrl, this.usernameParameter, username);
            redirectionUrl = CommonHelper.addParameter(redirectionUrl, ERROR_PARAMETER, errorMessage);
            logger.debug("redirectionUrl: {}", redirectionUrl);
            return HttpActionHelper.buildRedirectUrlAction(context, redirectionUrl);
        }
    }

    /**
     * Return the error message depending on the thrown exception. Can be overriden for other message computation.
     *
     * @param e the technical exception
     * @return the error message
     */
    protected String computeErrorMessage(final Exception e) {
        return e.getClass().getSimpleName();
    }

    public String getLoginUrl() {
        return this.loginUrl;
    }

    public void setLoginUrl(final String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getUsernameParameter() {
        return this.usernameParameter;
    }

    public void setUsernameParameter(final String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return this.passwordParameter;
    }

    public void setPasswordParameter(final String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }


    public String getCaptchaParameter() {
        return captchaParameter;
    }

    public void setCaptchaParameter(String captchaParameter) {
        this.captchaParameter = captchaParameter;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{callbackUrl='" + callbackUrl + "', name='" + getName()
                + "', loginUrl='" + loginUrl + "', usernameParameter='" + usernameParameter
                + "', passwordParameter='[PROTECTED]', captchaParameter='" + captchaParameter + "'}";
    }


}
