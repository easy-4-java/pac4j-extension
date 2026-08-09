package org.pac4j.core.ext.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for exception classes in the pac4j extension framework.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class ExceptionTests {

    @Test
    void captchaIncorrectExceptionShouldHoldMessage() {
        CaptchaIncorrectException ex = new CaptchaIncorrectException("captcha wrong");
        assertThat(ex.getMessage()).isEqualTo("captcha wrong");
        assertThat(ex.getCause()).isNull();
    }

    @Test
    void captchaIncorrectExceptionShouldHoldCause() {
        RuntimeException cause = new RuntimeException("root");
        CaptchaIncorrectException ex = new CaptchaIncorrectException("captcha wrong", cause);
        assertThat(ex.getMessage()).isEqualTo("captcha wrong");
        assertThat(ex.getCause()).isEqualTo(cause);
    }

    @Test
    void captchaNotFoundExceptionShouldHoldMessage() {
        CaptchaNotFoundException ex = new CaptchaNotFoundException("captcha missing");
        assertThat(ex.getMessage()).isEqualTo("captcha missing");
        assertThat(ex.getCause()).isNull();
    }

    @Test
    void captchaNotFoundExceptionShouldHoldCause() {
        RuntimeException cause = new RuntimeException("root");
        CaptchaNotFoundException ex = new CaptchaNotFoundException("captcha missing", cause);
        assertThat(ex.getMessage()).isEqualTo("captcha missing");
        assertThat(ex.getCause()).isEqualTo(cause);
    }

    @Test
    void methodNotSupportedExceptionShouldHoldMessage() {
        MethodNotSupportedException ex = new MethodNotSupportedException("method not supported");
        assertThat(ex.getMessage()).isEqualTo("method not supported");
    }

    @Test
    void overRetryRemindExceptionShouldHoldMessage() {
        OverRetryRemindException ex = new OverRetryRemindException("too many retries");
        assertThat(ex.getMessage()).isEqualTo("too many retries");
        assertThat(ex.getCause()).isNull();
    }

    @Test
    void overRetryRemindExceptionShouldHoldCause() {
        RuntimeException cause = new RuntimeException("root");
        OverRetryRemindException ex = new OverRetryRemindException("too many retries", cause);
        assertThat(ex.getMessage()).isEqualTo("too many retries");
        assertThat(ex.getCause()).isEqualTo(cause);
    }

    @Test
    void usernameNotFoundExceptionShouldHoldMessage() {
        UsernameNotFoundException ex = new UsernameNotFoundException("user not found");
        assertThat(ex.getMessage()).isEqualTo("user not found");
        assertThat(ex.getCause()).isNull();
    }

    @Test
    void usernameNotFoundExceptionShouldHoldCause() {
        RuntimeException cause = new RuntimeException("root");
        UsernameNotFoundException ex = new UsernameNotFoundException("user not found", cause);
        assertThat(ex.getMessage()).isEqualTo("user not found");
        assertThat(ex.getCause()).isEqualTo(cause);
    }
}
