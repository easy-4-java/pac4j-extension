package org.pac4j.core.ext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.CallContext;
import org.pac4j.core.credentials.Credentials;
import org.pac4j.core.ext.authentication.UsernamePasswordCaptchaAuthenticator;
import org.pac4j.core.ext.credentials.SignatureCredentials;
import org.pac4j.core.ext.credentials.UsernamePasswordCaptchaCredentials;
import org.pac4j.core.ext.credentials.extractor.SignatureParameterExtractor;
import org.pac4j.test.context.MockWebContext;
import org.pac4j.test.context.session.MockSessionStore;

class Pac4jBizCompatibilityTest {

    @Test
    void extractsSignatureWithPac4jCallContext() {
        MockWebContext webContext = MockWebContext.create()
                .setRequestMethod("POST")
                .addRequestParameter(Pac4jExtConstants.SIGNATURE_PARAM, "signed-value");
        webContext.setRequestContent("payload");
        CallContext callContext = new CallContext(webContext, new MockSessionStore());
        SignatureParameterExtractor extractor = new SignatureParameterExtractor(
                Pac4jExtConstants.SIGNATURE_PARAM, false, true, "UTF-8");

        Optional<Credentials> extracted = extractor.extract(callContext);

        assertTrue(extracted.isPresent());
        SignatureCredentials credentials = (SignatureCredentials) extracted.get();
        assertEquals("payload", credentials.getPayload());
        assertEquals("signed-value", credentials.getSignature());
    }

    @Test
    void validatesUsernamePasswordWithPac4jCallContext() {
        CallContext callContext = new CallContext(MockWebContext.create(), new MockSessionStore());
        UsernamePasswordCaptchaCredentials credentials =
                new UsernamePasswordCaptchaCredentials("easy4j", "easy4j");

        Optional<Credentials> validated = new UsernamePasswordCaptchaAuthenticator().validate(callContext, credentials);

        assertTrue(validated.isPresent());
        assertEquals("easy4j", validated.get().getUserProfile().getId());
    }

    @Test
    void comparesCaptchaCredentialsWithoutCrossTypeCast() {
        UsernamePasswordCaptchaCredentials first =
                new UsernamePasswordCaptchaCredentials("user", "password", "1234");
        UsernamePasswordCaptchaCredentials same =
                new UsernamePasswordCaptchaCredentials("user", "password", "1234");
        UsernamePasswordCaptchaCredentials differentCaptcha =
                new UsernamePasswordCaptchaCredentials("user", "password", "5678");

        assertEquals(first, same);
        assertEquals(first.hashCode(), same.hashCode());
        assertFalse(first.equals(differentCaptcha));
    }
}
