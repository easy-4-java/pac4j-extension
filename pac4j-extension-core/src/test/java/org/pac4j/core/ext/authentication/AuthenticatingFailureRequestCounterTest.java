package org.pac4j.core.ext.authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;

/**
 * Tests for {@link AuthenticatingFailureRequestCounter}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class AuthenticatingFailureRequestCounterTest {

    @Test
    void getShouldReturnCountFromRequestParameter() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        when(context.getRequestParameter("failureRetries")).thenReturn(Optional.of("5"));

        int count = counter.get(context, sessionStore, "retryKey");
        assertThat(count).isEqualTo(5);
    }

    @Test
    void getShouldReturnZeroWhenParameterMissing() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        when(context.getRequestParameter("failureRetries")).thenReturn(Optional.empty());

        int count = counter.get(context, sessionStore, "retryKey");
        assertThat(count).isEqualTo(0);
    }

    @Test
    void incrementShouldBeNoOp() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        WebContext context = mock(WebContext.class);
        SessionStore sessionStore = mock(SessionStore.class);
        // Should not throw
        counter.increment(context, sessionStore, "retryKey");
    }

    @Test
    void retryTimesKeyParameterShouldHaveDefault() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        assertThat(counter.getRetryTimesKeyParameter()).isEqualTo("failureRetries");
    }

    @Test
    void retryTimesKeyParameterShouldBeSettable() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        counter.setRetryTimesKeyParameter("customKey");
        assertThat(counter.getRetryTimesKeyParameter()).isEqualTo("customKey");
    }
}
