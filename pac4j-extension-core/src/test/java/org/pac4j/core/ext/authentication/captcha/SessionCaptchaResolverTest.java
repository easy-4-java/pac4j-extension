package org.pac4j.core.ext.authentication.captcha;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;

/**
 * Tests for {@link SessionCaptchaResolver}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class SessionCaptchaResolverTest {

    @Test
    void validCaptchaShouldReturnTrueWhenMatch() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        when(sessionStore.get(context, SessionCaptchaResolver.KAPTCHA_SESSION_ATTRIBUTE_NAME))
            .thenReturn(Optional.of("ABC123"));

        assertThat(resolver.validCaptcha(context, sessionStore, "ABC123")).isTrue();
    }

    @Test
    void validCaptchaShouldReturnTrueWhenMatchCaseInsensitive() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        when(sessionStore.get(context, SessionCaptchaResolver.KAPTCHA_SESSION_ATTRIBUTE_NAME))
            .thenReturn(Optional.of("ABC123"));

        assertThat(resolver.validCaptcha(context, sessionStore, "abc123")).isTrue();
    }

    @Test
    void validCaptchaShouldReturnFalseWhenNoMatch() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        when(sessionStore.get(context, SessionCaptchaResolver.KAPTCHA_SESSION_ATTRIBUTE_NAME))
            .thenReturn(Optional.of("ABC123"));

        assertThat(resolver.validCaptcha(context, sessionStore, "XYZ789")).isFalse();
    }

    @Test
    void validCaptchaShouldReturnFalseWhenEmpty() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);

        assertThat(resolver.validCaptcha(context, sessionStore, "")).isFalse();
        assertThat(resolver.validCaptcha(context, sessionStore, null)).isFalse();
    }

    @Test
    void validCaptchaShouldReturnFalseWhenNoSessionValue() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        when(sessionStore.get(context, SessionCaptchaResolver.KAPTCHA_SESSION_ATTRIBUTE_NAME))
            .thenReturn(Optional.empty());

        assertThat(resolver.validCaptcha(context, sessionStore, "ABC123")).isFalse();
    }

    @Test
    void setCaptchaShouldStoreTextAndDate() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        Date date = new Date();

        resolver.setCaptcha(context, sessionStore, "ABC123", date);
        verify(sessionStore).set(context, SessionCaptchaResolver.KAPTCHA_SESSION_ATTRIBUTE_NAME, "ABC123");
        verify(sessionStore).set(context, SessionCaptchaResolver.KAPTCHA_DATE_SESSION_ATTRIBUTE_NAME, date);
    }

    @Test
    void setCaptchaShouldStoreNullWhenTextEmpty() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);

        resolver.setCaptcha(context, sessionStore, "", new Date());
        verify(sessionStore).set(context, SessionCaptchaResolver.KAPTCHA_SESSION_ATTRIBUTE_NAME, null);
    }
}
