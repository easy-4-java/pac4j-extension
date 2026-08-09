package org.pac4j.spring.boot.utils;

import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Pac4jUrlUtils}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jUrlUtilsTest {

    @Test
    void constructRedirectUrlShouldAppendClientParameter() {
        String result = Pac4jUrlUtils.constructRedirectUrl("http://example.com", "client_name", "myClient");
        assertThat(result).isEqualTo("http://example.com?client_name=myClient");
    }

    @Test
    void constructRedirectUrlShouldUseAmpersandWhenQueryExists() {
        String result = Pac4jUrlUtils.constructRedirectUrl("http://example.com?existing=param", "client_name", "myClient");
        assertThat(result).isEqualTo("http://example.com?existing=param&client_name=myClient");
    }

    @Test
    void constructRedirectUrlWithEncodeShouldEncodeServiceUrl() {
        String result = Pac4jUrlUtils.constructRedirectUrl("http://example.com/path", "client_name", "myClient", true);
        assertThat(result).contains("client_name=myClient");
    }

    @Test
    void constructRedirectUrlWithoutEncodeShouldNotEncodeServiceUrl() {
        String result = Pac4jUrlUtils.constructRedirectUrl("http://example.com/path", "client_name", "myClient", false);
        assertThat(result).startsWith("http://example.com/path");
        assertThat(result).contains("client_name=myClient");
    }

    @Test
    void urlEncodeShouldEncodeValue() {
        String result = Pac4jUrlUtils.urlEncode("hello world");
        assertThat(result).isEqualTo("hello+world");
    }

    @Test
    void urlEncodeShouldEncodeSpecialCharacters() {
        String result = Pac4jUrlUtils.urlEncode("http://example.com/path?q=1");
        assertThat(result).contains("%3A");
    }

    @Test
    void sendRedirectShouldNotThrow() throws Exception {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Pac4jUrlUtils.sendRedirect(response, "http://example.com");
        Mockito.verify(response).sendRedirect("http://example.com");
    }
}
