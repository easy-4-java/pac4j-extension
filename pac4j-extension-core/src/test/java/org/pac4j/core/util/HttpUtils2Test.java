package org.pac4j.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link HttpUtils2}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class HttpUtils2Test {

    @Test
    void buildURLShouldReturnBaseURLWhenParamsNull() {
        String result = HttpUtils2.buildURL("http://example.com", null);
        assertThat(result).isEqualTo("http://example.com");
    }

    @Test
    void buildURLShouldAppendParams() {
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        String result = HttpUtils2.buildURL("http://example.com", params);
        assertThat(result).startsWith("http://example.com?");
        assertThat(result).contains("key1=value1");
        assertThat(result).contains("key2=value2");
    }

    @Test
    void buildURLShouldUseAmpersandWhenQueryExists() {
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        String result = HttpUtils2.buildURL("http://example.com?existing=param", params);
        assertThat(result).contains("&key1=value1");
    }

    @Test
    void getSessionIDShouldReturnEmptyWhenConnectionNull() {
        String sessionId = HttpUtils2.getSessionID(null);
        assertThat(sessionId).isEmpty();
    }
}
