package org.pac4j.core.ext.authentication;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AuthenticationRequest}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class AuthenticationRequestTest {

    @Test
    void constructorShouldSetAllFields() {
        AuthenticationRequest request = new AuthenticationRequest("user", "pass", "captcha");
        assertThat(request.getUsername()).isEqualTo("user");
        assertThat(request.getPassword()).isEqualTo("pass");
        assertThat(request.getCaptcha()).isEqualTo("captcha");
    }

    @Test
    void settersShouldUpdateFields() {
        AuthenticationRequest request = new AuthenticationRequest("user", "pass", "captcha");
        request.setUsername("newUser");
        request.setPassword("newPass");
        request.setCaptcha("newCaptcha");
        assertThat(request.getUsername()).isEqualTo("newUser");
        assertThat(request.getPassword()).isEqualTo("newPass");
        assertThat(request.getCaptcha()).isEqualTo("newCaptcha");
    }

    @Test
    void constructorShouldHandleNullValues() {
        AuthenticationRequest request = new AuthenticationRequest(null, null, null);
        assertThat(request.getUsername()).isNull();
        assertThat(request.getPassword()).isNull();
        assertThat(request.getCaptcha()).isNull();
    }
}
