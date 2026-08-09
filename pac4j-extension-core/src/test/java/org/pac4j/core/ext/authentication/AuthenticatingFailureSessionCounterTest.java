package org.pac4j.core.ext.authentication;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AuthenticatingFailureSessionCounter}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class AuthenticatingFailureSessionCounterTest {

    @Test
    void constructorShouldCreateInstance() {
        AuthenticatingFailureSessionCounter counter = new AuthenticatingFailureSessionCounter();
        assertThat(counter).isNotNull();
    }

    @Test
    void shouldImplementAuthenticatingFailureCounter() {
        AuthenticatingFailureSessionCounter counter = new AuthenticatingFailureSessionCounter();
        assertThat(counter).isInstanceOf(AuthenticatingFailureCounter.class);
    }
}
