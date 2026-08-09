package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jOAuthFacebookClientProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthFacebookClientPropertiesTest {

    @Test
    void defaultValuesShouldBeCorrect() {
        Pac4jOAuthFacebookClientProperties props = new Pac4jOAuthFacebookClientProperties();
        assertThat(props.getFields()).isNotNull();
        assertThat(props.getLimit()).isGreaterThanOrEqualTo(0);
        assertThat(props.isRequiresExtendedToken()).isFalse();
        assertThat(props.isUseAppsecretProof()).isFalse();
    }

    @Test
    void settersShouldUpdateFields() {
        Pac4jOAuthFacebookClientProperties props = new Pac4jOAuthFacebookClientProperties();
        props.setFields("id,name,email");
        props.setLimit(100);
        props.setRequiresExtendedToken(true);
        props.setUseAppsecretProof(true);

        assertThat(props.getFields()).isEqualTo("id,name,email");
        assertThat(props.getLimit()).isEqualTo(100);
        assertThat(props.isRequiresExtendedToken()).isTrue();
        assertThat(props.isUseAppsecretProof()).isTrue();
    }

    @Test
    void shouldInheritFromBaseProperties() {
        Pac4jOAuthFacebookClientProperties props = new Pac4jOAuthFacebookClientProperties();
        props.setKey("fbKey");
        props.setSecret("fbSecret");
        assertThat(props.getKey()).isEqualTo("fbKey");
        assertThat(props.getSecret()).isEqualTo("fbSecret");
    }
}
