package org.pac4j.core.ext.profile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link TokenProfile}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class TokenProfileTest {

    @Test
    void constructorShouldCreateInstance() {
        TokenProfile profile = new TokenProfile();
        assertThat(profile).isNotNull();
    }

    @Test
    void profileShouldAllowSettingId() {
        TokenProfile profile = new TokenProfile();
        profile.setId("user123");
        assertThat(profile.getId()).isEqualTo("user123");
    }

    @Test
    void profileShouldAllowAddingAttributes() {
        TokenProfile profile = new TokenProfile();
        profile.addAttribute("key", "value");
        assertThat(profile.getAttribute("key")).isEqualTo("value");
    }
}
