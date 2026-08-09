package org.pac4j.core.ext.credentials;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SignatureCredentials}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class SignatureCredentialsTest {

    @Test
    void constructorShouldSetPayloadAndSignature() {
        SignatureCredentials credentials = new SignatureCredentials("testPayload", "testSignature");
        assertThat(credentials.getPayload()).isEqualTo("testPayload");
        assertThat(credentials.getSignature()).isEqualTo("testSignature");
    }

    @Test
    void equalsShouldReturnTrueForSameSignature() {
        SignatureCredentials c1 = new SignatureCredentials("payload", "sig1");
        SignatureCredentials c2 = new SignatureCredentials("other", "sig1");
        assertThat(c1).isEqualTo(c2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentSignature() {
        SignatureCredentials c1 = new SignatureCredentials("payload", "sig1");
        SignatureCredentials c2 = new SignatureCredentials("payload", "sig2");
        assertThat(c1).isNotEqualTo(c2);
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        SignatureCredentials c1 = new SignatureCredentials("payload", "sig1");
        assertThat(c1.equals(null)).isFalse();
    }

    @Test
    void equalsShouldReturnTrueForSameInstance() {
        SignatureCredentials c1 = new SignatureCredentials("payload", "sig1");
        assertThat(c1.equals(c1)).isTrue();
    }

    @Test
    void equalsShouldReturnFalseForDifferentType() {
        SignatureCredentials c1 = new SignatureCredentials("payload", "sig1");
        assertThat(c1.equals("string")).isFalse();
    }

    @Test
    void hashCodeShouldBeBasedOnSignature() {
        SignatureCredentials c1 = new SignatureCredentials("payload", "sig1");
        SignatureCredentials c2 = new SignatureCredentials("other", "sig1");
        assertThat(c1.hashCode()).isEqualTo(c2.hashCode());
    }

    @Test
    void hashCodeShouldReturnZeroForNullSignature() {
        SignatureCredentials c1 = new SignatureCredentials("payload", null);
        assertThat(c1.hashCode()).isEqualTo(0);
    }

    @Test
    void toStringShouldContainPayloadAndSignature() {
        SignatureCredentials c1 = new SignatureCredentials("payload", "sig1");
        String str = c1.toString();
        assertThat(str).contains("payload");
        assertThat(str).contains("sig1");
    }
}
