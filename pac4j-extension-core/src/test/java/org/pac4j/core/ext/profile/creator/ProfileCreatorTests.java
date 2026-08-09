package org.pac4j.core.ext.profile.creator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.ext.profile.TokenProfile;
import org.pac4j.core.profile.UserProfile;

/**
 * Tests for {@link TokenProfileCreator} and {@link SignatureProfileCreator}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class ProfileCreatorTests {

    @Test
    void tokenProfileCreatorShouldReturnProfileFromCredentials() {
        TokenProfileCreator creator = TokenProfileCreator.INSTANCE;
        TokenCredentials credentials = new TokenCredentials("testToken");
        TokenProfile profile = new TokenProfile();
        profile.setId("user1");
        credentials.setUserProfile(profile);

        Optional<UserProfile> result = creator.create(null, credentials);
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo("user1");
    }

    @Test
    void tokenProfileCreatorShouldReturnEmptyWhenNoProfile() {
        TokenProfileCreator creator = TokenProfileCreator.INSTANCE;
        TokenCredentials credentials = new TokenCredentials("testToken");

        Optional<UserProfile> result = creator.create(null, credentials);
        assertThat(result).isEmpty();
    }

    @Test
    void signatureProfileCreatorShouldReturnProfileFromCredentials() {
        SignatureProfileCreator creator = SignatureProfileCreator.INSTANCE;
        TokenCredentials credentials = new TokenCredentials("testToken");
        TokenProfile profile = new TokenProfile();
        profile.setId("user1");
        credentials.setUserProfile(profile);

        Optional<UserProfile> result = creator.create(null, credentials);
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo("user1");
    }

    @Test
    void signatureProfileCreatorShouldReturnEmptyWhenNoProfile() {
        SignatureProfileCreator creator = SignatureProfileCreator.INSTANCE;
        TokenCredentials credentials = new TokenCredentials("testToken");

        Optional<UserProfile> result = creator.create(null, credentials);
        assertThat(result).isEmpty();
    }
}
