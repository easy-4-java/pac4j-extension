package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jOAuthCasClientProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthCasClientPropertiesTest {

    @Test
    void defaultValuesShouldBeCorrect() {
        Pac4jOAuthCasClientProperties props = new Pac4jOAuthCasClientProperties();
        assertThat(props.getCasOAuthUrl()).isNull();
        assertThat(props.getCasLogoutUrl()).isNull();
        assertThat(props.isSpringSecurityCompliant()).isFalse();
        assertThat(props.isImplicitFlow()).isFalse();
    }

    @Test
    void settersShouldUpdateFields() {
        Pac4jOAuthCasClientProperties props = new Pac4jOAuthCasClientProperties();
        props.setCasOAuthUrl("http://cas.example.com/oauth2.0");
        props.setCasLogoutUrl("http://cas.example.com/logout");
        props.setSpringSecurityCompliant(true);
        props.setImplicitFlow(true);

        assertThat(props.getCasOAuthUrl()).isEqualTo("http://cas.example.com/oauth2.0");
        assertThat(props.getCasLogoutUrl()).isEqualTo("http://cas.example.com/logout");
        assertThat(props.isSpringSecurityCompliant()).isTrue();
        assertThat(props.isImplicitFlow()).isTrue();
    }

    @Test
    void shouldInheritFromBaseProperties() {
        Pac4jOAuthCasClientProperties props = new Pac4jOAuthCasClientProperties();
        props.setKey("testKey");
        props.setSecret("testSecret");
        assertThat(props.getKey()).isEqualTo("testKey");
        assertThat(props.getSecret()).isEqualTo("testSecret");
    }
}
