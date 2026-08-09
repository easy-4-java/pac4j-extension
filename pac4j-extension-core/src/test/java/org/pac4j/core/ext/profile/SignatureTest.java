package org.pac4j.core.ext.profile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Signature}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class SignatureTest {

    private static class TestSignature extends Signature {
        protected TestSignature(String rawResponse) {
            super(rawResponse);
        }
    }

    @Test
    void getRawResponseShouldReturnProvidedValue() {
        Signature sig = new TestSignature("data=value&signature=abc");
        assertThat(sig.getRawResponse()).isEqualTo("data=value&signature=abc");
    }

    @Test
    void getRawResponseShouldThrowWhenNull() {
        Signature sig = new TestSignature(null);
        assertThatThrownBy(sig::getRawResponse)
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getParameterShouldExtractParameterValue() {
        Signature sig = new TestSignature("data=value&signature=abc");
        assertThat(sig.getParameter("data")).isEqualTo("value");
        assertThat(sig.getParameter("signature")).isEqualTo("abc");
    }

    @Test
    void getParameterShouldReturnNullForMissingParameter() {
        Signature sig = new TestSignature("data=value");
        assertThat(sig.getParameter("missing")).isNull();
    }
}
