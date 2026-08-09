package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jOAuthOkClientProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthOkClientPropertiesTest {

    @Test
    void defaultValuesShouldBeCorrect() {
        Pac4jOAuthOkClientProperties props = new Pac4jOAuthOkClientProperties();
        assertThat(props.getPublicKey()).isNull();
    }

    @Test
    void settersShouldUpdateFields() {
        Pac4jOAuthOkClientProperties props = new Pac4jOAuthOkClientProperties();
        props.setPublicKey("testPublicKey");
        assertThat(props.getPublicKey()).isEqualTo("testPublicKey");
    }

    @Test
    void shouldInheritFromBaseProperties() {
        Pac4jOAuthOkClientProperties props = new Pac4jOAuthOkClientProperties();
        props.setKey("okKey");
        props.setSecret("okSecret");
        assertThat(props.getKey()).isEqualTo("okKey");
        assertThat(props.getSecret()).isEqualTo("okSecret");
    }
}
