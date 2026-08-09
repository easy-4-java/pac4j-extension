package org.pac4j.core.ext.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.WebContext;

/**
 * Tests for {@link WebUtils}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class WebUtilsTest {

    @Test
    void buildURLShouldReturnBaseURLWhenParamsNull() {
        String result = WebUtils.buildURL("http://example.com", null);
        assertThat(result).isEqualTo("http://example.com");
    }

    @Test
    void buildURLShouldAppendParams() {
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        String result = WebUtils.buildURL("http://example.com", params);
        assertThat(result).startsWith("http://example.com?");
        assertThat(result).contains("key1=value1");
        assertThat(result).contains("key2=value2");
    }

    @Test
    void buildURLShouldUseAmpersandWhenQueryExists() {
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        String result = WebUtils.buildURL("http://example.com?existing=param", params);
        assertThat(result).contains("&key1=value1");
    }

    @Test
    void isAjaxRequestShouldReturnTrueForAjaxHeader() {
        WebContext context = mock(WebContext.class);
        when(context.getRequestHeader(HttpConstants.AJAX_HEADER_NAME))
            .thenReturn(Optional.of(HttpConstants.AJAX_HEADER_VALUE));
        assertThat(WebUtils.isAjaxRequest(context)).isTrue();
    }

    @Test
    void isAjaxRequestShouldReturnFalseWhenNoHeader() {
        WebContext context = mock(WebContext.class);
        when(context.getRequestHeader(HttpConstants.AJAX_HEADER_NAME))
            .thenReturn(Optional.empty());
        assertThat(WebUtils.isAjaxRequest(context)).isFalse();
    }

    @Test
    void isContentTypeJsonShouldReturnTrueForJsonContent() {
        WebContext context = mock(WebContext.class);
        when(context.getRequestHeader(HttpConstants.CONTENT_TYPE_HEADER))
            .thenReturn(Optional.of("application/json"));
        assertThat(WebUtils.isContentTypeJson(context)).isTrue();
    }

    @Test
    void isContentTypeJsonShouldReturnFalseForNonJsonContent() {
        WebContext context = mock(WebContext.class);
        when(context.getRequestHeader(HttpConstants.CONTENT_TYPE_HEADER))
            .thenReturn(Optional.of("text/html"));
        assertThat(WebUtils.isContentTypeJson(context)).isFalse();
    }

    @Test
    void isContentTypeJsonShouldReturnFalseWhenNoHeader() {
        WebContext context = mock(WebContext.class);
        when(context.getRequestHeader(HttpConstants.CONTENT_TYPE_HEADER))
            .thenReturn(Optional.empty());
        assertThat(WebUtils.isContentTypeJson(context)).isFalse();
    }

    @Test
    void isPostRequestShouldReturnTrueForPost() {
        WebContext context = mock(WebContext.class);
        when(context.getRequestMethod()).thenReturn("POST");
        assertThat(WebUtils.isPostRequest(context)).isTrue();
    }

    @Test
    void isPostRequestShouldReturnFalseForGet() {
        WebContext context = mock(WebContext.class);
        when(context.getRequestMethod()).thenReturn("GET");
        assertThat(WebUtils.isPostRequest(context)).isFalse();
    }
}
