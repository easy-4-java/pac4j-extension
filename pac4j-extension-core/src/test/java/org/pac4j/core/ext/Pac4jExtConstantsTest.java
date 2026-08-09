package org.pac4j.core.ext;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jExtConstants}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jExtConstantsTest {

    @Test
    void captchaConstantShouldBeCorrect() {
        assertThat(Pac4jExtConstants.CAPTCHA).isEqualTo("captcha");
    }

    @Test
    void tokenConstantShouldBeCorrect() {
        assertThat(Pac4jExtConstants.TOKEN).isEqualTo("token");
    }

    @Test
    void signatureParamConstantShouldBeCorrect() {
        assertThat(Pac4jExtConstants.SIGNATURE_PARAM).isEqualTo("signature");
    }

    @Test
    void retryTimesKeyAttributeNameShouldBeCorrect() {
        assertThat(Pac4jExtConstants.RETRY_TIMES_KEY_ATTRIBUTE_NAME).isEqualTo("pac4jLoginFailureRetries");
    }

    @Test
    void retryTimesKeyParamNameShouldBeCorrect() {
        assertThat(Pac4jExtConstants.RETRY_TIMES_KEY_PARAM_NAME).isEqualTo("failureRetries");
    }
}
