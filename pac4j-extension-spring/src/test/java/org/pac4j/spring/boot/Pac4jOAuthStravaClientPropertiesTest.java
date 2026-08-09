package org.pac4j.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Pac4jOAuthStravaClientProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class Pac4jOAuthStravaClientPropertiesTest {

    @Test
    void defaultValuesShouldBeCorrect() {
        Pac4jOAuthStravaClientProperties props = new Pac4jOAuthStravaClientProperties();
        assertThat(props.getApprovalPrompt()).isEqualTo("auto");
    }

    @Test
    void settersShouldUpdateFields() {
        Pac4jOAuthStravaClientProperties props = new Pac4jOAuthStravaClientProperties();
        props.setApprovalPrompt("force");
        assertThat(props.getApprovalPrompt()).isEqualTo("force");
    }

    @Test
    void shouldInheritFromBaseProperties() {
        Pac4jOAuthStravaClientProperties props = new Pac4jOAuthStravaClientProperties();
        props.setKey("stravaKey");
        props.setSecret("stravaSecret");
        assertThat(props.getKey()).isEqualTo("stravaKey");
        assertThat(props.getSecret()).isEqualTo("stravaSecret");
    }
}
