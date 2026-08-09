package org.pac4j.core.ext.profile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Token}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class TokenTest {

    private static class TestToken extends Token {
        protected TestToken(String rawResponse) {
            super(rawResponse);
        }
    }

    @Test
    void getRawResponseShouldReturnProvidedValue() {
        Token token = new TestToken("access_token=abc123&token_type=bearer");
        assertThat(token.getRawResponse()).isEqualTo("access_token=abc123&token_type=bearer");
    }

    @Test
    void getRawResponseShouldThrowWhenNull() {
        Token token = new TestToken(null);
        assertThatThrownBy(token::getRawResponse)
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getParameterShouldExtractParameterValue() {
        Token token = new TestToken("access_token=abc123&token_type=bearer");
        assertThat(token.getParameter("access_token")).isEqualTo("abc123");
        assertThat(token.getParameter("token_type")).isEqualTo("bearer");
    }

    @Test
    void getParameterShouldReturnNullForMissingParameter() {
        Token token = new TestToken("access_token=abc123");
        assertThat(token.getParameter("missing")).isNull();
    }

    @Test
    void getParameterShouldReturnNullForTrailingEquals() {
        Token token = new TestToken("access_token=abc&empty=");
        String result = token.getParameter("empty");
        assertThat(result).isNull();
    }
}
