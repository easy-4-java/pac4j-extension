package org.pac4j.core.ext.profile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SignatureProfile}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class SignatureProfileTest {

    @Test
    void constructorShouldCreateInstance() {
        SignatureProfile profile = new SignatureProfile();
        assertThat(profile).isNotNull();
    }

    @Test
    void profileShouldAllowSettingId() {
        SignatureProfile profile = new SignatureProfile();
        profile.setId("user123");
        assertThat(profile.getId()).isEqualTo("user123");
    }

    @Test
    void profileShouldAllowAddingAttributes() {
        SignatureProfile profile = new SignatureProfile();
        profile.addAttribute("key", "value");
        assertThat(profile.getAttribute("key")).isEqualTo("value");
    }
}
