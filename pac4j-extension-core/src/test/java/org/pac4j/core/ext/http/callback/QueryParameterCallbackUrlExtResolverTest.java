package org.pac4j.core.ext.http.callback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.http.url.UrlResolver;

/**
 * Tests for {@link QueryParameterCallbackUrlExtResolver}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class QueryParameterCallbackUrlExtResolverTest {

    @Test
    void shouldReturnCallbackUrlWhenAlwaysUse() {
        Map<String, String> customParams = new HashMap<>();
        QueryParameterCallbackUrlExtResolver resolver = new QueryParameterCallbackUrlExtResolver(
            true, "http://callback.example.com", customParams);
        UrlResolver urlResolver = mock(UrlResolver.class);
        WebContext context = mock(WebContext.class);

        String result = resolver.compute(urlResolver, "http://original.com", "clientName", context);
        assertThat(result).startsWith("http://callback.example.com");
        assertThat(result).contains("client_name=clientName");
    }

    @Test
    void shouldAppendCustomParams() {
        Map<String, String> customParams = new HashMap<>();
        customParams.put("customKey", "customValue");
        QueryParameterCallbackUrlExtResolver resolver = new QueryParameterCallbackUrlExtResolver(
            true, "http://callback.example.com", customParams);
        UrlResolver urlResolver = mock(UrlResolver.class);
        WebContext context = mock(WebContext.class);

        String result = resolver.compute(urlResolver, "http://original.com", "clientName", context);
        assertThat(result).contains("customKey=customValue");
    }

    @Test
    void defaultConstructorShouldNotAlwaysUseCallbackUrl() {
        QueryParameterCallbackUrlExtResolver resolver = new QueryParameterCallbackUrlExtResolver();
        assertThat(resolver.isAlwaysUseCallbackUrl()).isFalse();
    }

    @Test
    void getCallbackUrlShouldReturnConfiguredUrl() {
        Map<String, String> customParams = new HashMap<>();
        QueryParameterCallbackUrlExtResolver resolver = new QueryParameterCallbackUrlExtResolver(
            true, "http://callback.example.com", customParams);
        assertThat(resolver.getCallbackUrl()).isEqualTo("http://callback.example.com");
    }
}
