package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jOAuthClientProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthClientPropertiesTest {

    @Test
    void responseTypeCodeShouldBeCorrect() {
        assertThat(Pac4jOAuthClientProperties.RESPONSE_TYPE_CODE).isEqualTo("code");
    }

    @Test
    void defaultValuesShouldBeCorrect() {
        Pac4jOAuthClientProperties props = new Pac4jOAuthClientProperties();
        assertThat(props.getResponseType()).isEqualTo("code");
        assertThat(props.isTokenAsHeader()).isFalse();
        assertThat(props.isHasGrantType()).isFalse();
        assertThat(props.isWithState()).isFalse();
        assertThat(props.getConnectTimeout()).isPositive();
        assertThat(props.getReadTimeout()).isPositive();
    }

    @Test
    void settersShouldUpdateFields() {
        Pac4jOAuthClientProperties props = new Pac4jOAuthClientProperties();
        props.setCallbackUrl("http://callback.example.com");
        props.setName("testClient");
        props.setKey("testKey");
        props.setSecret("testSecret");
        props.setResponseType("token");
        props.setScope("read write");

        assertThat(props.getCallbackUrl()).isEqualTo("http://callback.example.com");
        assertThat(props.getName()).isEqualTo("testClient");
        assertThat(props.getKey()).isEqualTo("testKey");
        assertThat(props.getSecret()).isEqualTo("testSecret");
        assertThat(props.getResponseType()).isEqualTo("token");
        assertThat(props.getScope()).isEqualTo("read write");
    }

    @Test
    void customParamsShouldBeInitialized() {
        Pac4jOAuthClientProperties props = new Pac4jOAuthClientProperties();
        assertThat(props.getCustomParams()).isNotNull().isEmpty();
    }

    @Test
    void profileAttrsShouldBeInitialized() {
        Pac4jOAuthClientProperties props = new Pac4jOAuthClientProperties();
        assertThat(props.getProfileAttrs()).isNotNull().isEmpty();
    }
}
