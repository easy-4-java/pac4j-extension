package org.pac4j.core.ext.http.callback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.http.url.UrlResolver;

/**
 * Tests for {@link PathParameterCallbackUrlExtResolver}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class PathParameterCallbackUrlExtResolverTest {

    @Test
    void shouldReturnCallbackUrlWhenAlwaysUse() {
        PathParameterCallbackUrlExtResolver resolver = new PathParameterCallbackUrlExtResolver(true, "http://callback.example.com");
        UrlResolver urlResolver = mock(UrlResolver.class);
        WebContext context = mock(WebContext.class);

        String result = resolver.compute(urlResolver, "http://original.com", "clientName", context);
        assertThat(result).isEqualTo("http://callback.example.com");
    }

    @Test
    void shouldCallSuperWhenNotAlwaysUse() {
        PathParameterCallbackUrlExtResolver resolver = new PathParameterCallbackUrlExtResolver(false, "http://callback.example.com");
        UrlResolver urlResolver = mock(UrlResolver.class);
        WebContext context = mock(WebContext.class);
        when(urlResolver.compute("http://original.com", context)).thenReturn("http://resolved.com");

        String result = resolver.compute(urlResolver, "http://original.com", "clientName", context);
        assertThat(result).isNotNull();
    }

    @Test
    void defaultConstructorShouldNotAlwaysUseCallbackUrl() {
        PathParameterCallbackUrlExtResolver resolver = new PathParameterCallbackUrlExtResolver();
        assertThat(resolver.isAlwaysUseCallbackUrl()).isFalse();
    }

    @Test
    void getCallbackUrlShouldReturnConfiguredUrl() {
        PathParameterCallbackUrlExtResolver resolver = new PathParameterCallbackUrlExtResolver(true, "http://callback.example.com");
        assertThat(resolver.getCallbackUrl()).isEqualTo("http://callback.example.com");
    }
}
