package org.pac4j.core.ext.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ContentType}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class ContentTypeTest {

    @Test
    void applicationJsonShouldBeCorrect() {
        assertThat(ContentType.APPLICATION_JSON).isEqualTo("application/json");
    }

    @Test
    void applicationXmlShouldBeCorrect() {
        assertThat(ContentType.APPLICATION_XML).isEqualTo("application/xml");
    }

    @Test
    void textHtmlShouldBeCorrect() {
        assertThat(ContentType.TEXT_HTML).isEqualTo("text/html");
    }

    @Test
    void textPlainShouldBeCorrect() {
        assertThat(ContentType.TEXT_PLAIN).isEqualTo("text/plain");
    }

    @Test
    void multipartFormDataShouldBeCorrect() {
        assertThat(ContentType.MULTIPART_FORM_DATA).isEqualTo("multipart/form-data");
    }

    @Test
    void wildcardShouldBeCorrect() {
        assertThat(ContentType.WILDCARD).isEqualTo("*/*");
    }

    @Test
    void utf8ShouldBeCorrect() {
        assertThat(ContentType.UTF_8).isEqualTo("UTF-8");
    }

    @Test
    void applicationFormUrlencodedShouldBeCorrect() {
        assertThat(ContentType.APPLICATION_FORM_URLENCODED).isEqualTo("application/x-www-form-urlencoded");
    }

    @Test
    void applicationOctetStreamShouldBeCorrect() {
        assertThat(ContentType.APPLICATION_OCTET_STREAM).isEqualTo("application/octet-stream");
    }

    @Test
    void textJsonShouldBeCorrect() {
        assertThat(ContentType.TEXT_JSON).isEqualTo("text/json");
    }

    @Test
    void textXmlShouldBeCorrect() {
        assertThat(ContentType.TEXT_XML).isEqualTo("text/xml");
    }
}
