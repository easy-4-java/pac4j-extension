package org.pac4j.core.ext.authentication.captcha;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;

/**
 * Tests for {@link NullCaptchaResolver}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class NullCaptchaResolverTest {

    @Test
    void validCaptchaShouldAlwaysReturnTrue() {
        NullCaptchaResolver resolver = new NullCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        assertThat(resolver.validCaptcha(context, sessionStore, "anyCaptcha")).isTrue();
    }

    @Test
    void setCaptchaShouldNotThrow() {
        NullCaptchaResolver resolver = new NullCaptchaResolver();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        resolver.setCaptcha(context, sessionStore, "captcha", new Date());
    }
}
