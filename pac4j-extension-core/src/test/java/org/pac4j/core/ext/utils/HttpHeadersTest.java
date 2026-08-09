package org.pac4j.core.ext.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link HttpHeaders}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class HttpHeadersTest {

    @Test
    void acceptShouldBeCorrect() {
        assertThat(HttpHeaders.ACCEPT).isEqualTo("Accept");
    }

    @Test
    void authorizationShouldBeCorrect() {
        assertThat(HttpHeaders.AUTHORIZATION).isEqualTo("Authorization");
    }

    @Test
    void contentTypeShouldBeCorrect() {
        assertThat(HttpHeaders.CONTENT_TYPE).isEqualTo("Content-Type");
    }

    @Test
    void contentLengthShouldBeCorrect() {
        assertThat(HttpHeaders.CONTENT_LENGTH).isEqualTo("Content-Length");
    }

    @Test
    void userAgentShouldBeCorrect() {
        assertThat(HttpHeaders.USER_AGENT).isEqualTo("User-Agent");
    }

    @Test
    void hostShouldBeCorrect() {
        assertThat(HttpHeaders.HOST).isEqualTo("Host");
    }

    @Test
    void cacheControlShouldBeCorrect() {
        assertThat(HttpHeaders.CACHE_CONTROL).isEqualTo("Cache-Control");
    }

    @Test
    void locationShouldBeCorrect() {
        assertThat(HttpHeaders.LOCATION).isEqualTo("Location");
    }

    @Test
    void wwwAuthenticateShouldBeCorrect() {
        assertThat(HttpHeaders.WWW_AUTHENTICATE).isEqualTo("WWW-Authenticate");
    }

    @Test
    void xForwardedForShouldBeCorrect() {
        assertThat(HttpHeaders.X_FORWARDED_FOR).isEqualTo("x-forwarded-for");
    }

    @Test
    void xRequestedWithShouldBeCorrect() {
        assertThat(HttpHeaders.X_REQUESTED_WITH).isEqualTo("X-Requested-With");
    }
}
