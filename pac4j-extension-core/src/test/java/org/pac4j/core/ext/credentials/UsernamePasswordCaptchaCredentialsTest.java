package org.pac4j.core.ext.credentials;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link UsernamePasswordCaptchaCredentials}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class UsernamePasswordCaptchaCredentialsTest {

    @Test
    void constructorWithUsernameAndPasswordShouldSetFields() {
        UsernamePasswordCaptchaCredentials credentials = new UsernamePasswordCaptchaCredentials("user", "pass");
        assertThat(credentials.getUsername()).isEqualTo("user");
        assertThat(credentials.getPassword()).isEqualTo("pass");
        assertThat(credentials.getCaptcha()).isNull();
    }

    @Test
    void constructorWithCaptchaShouldSetAllFields() {
        UsernamePasswordCaptchaCredentials credentials = new UsernamePasswordCaptchaCredentials("user", "pass", "captcha123");
        assertThat(credentials.getUsername()).isEqualTo("user");
        assertThat(credentials.getPassword()).isEqualTo("pass");
        assertThat(credentials.getCaptcha()).isEqualTo("captcha123");
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        UsernamePasswordCaptchaCredentials c1 = new UsernamePasswordCaptchaCredentials("user", "pass");
        assertThat(c1.equals(null)).isFalse();
    }

    @Test
    void equalsShouldReturnTrueForSameInstance() {
        UsernamePasswordCaptchaCredentials c1 = new UsernamePasswordCaptchaCredentials("user", "pass");
        assertThat(c1.equals(c1)).isTrue();
    }

    @Test
    void equalsShouldReturnFalseForDifferentType() {
        UsernamePasswordCaptchaCredentials c1 = new UsernamePasswordCaptchaCredentials("user", "pass");
        assertThat(c1.equals("string")).isFalse();
    }

    @Test
    void hashCodeShouldBeConsistentForSameInput() {
        UsernamePasswordCaptchaCredentials c1 = new UsernamePasswordCaptchaCredentials("user", "pass", "cap");
        UsernamePasswordCaptchaCredentials c2 = new UsernamePasswordCaptchaCredentials("user", "pass", "cap");
        assertThat(c1.hashCode()).isEqualTo(c2.hashCode());
    }

    @Test
    void toStringShouldContainUsername() {
        UsernamePasswordCaptchaCredentials c1 = new UsernamePasswordCaptchaCredentials("user", "pass", "cap");
        String str = c1.toString();
        assertThat(str).contains("user");
    }

    @Test
    void toStringShouldMaskPasswordAndCaptcha() {
        UsernamePasswordCaptchaCredentials c1 = new UsernamePasswordCaptchaCredentials("user", "pass", "cap");
        String str = c1.toString();
        assertThat(str).contains("[PROTECTED]");
    }
}
