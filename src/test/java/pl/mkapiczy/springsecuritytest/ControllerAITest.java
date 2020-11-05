package pl.mkapiczy.springsecuritytest;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 11111)
class ControllerAITest {
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        WireMock.reset();
        WireMock.stubFor(post(urlEqualTo("events-a")).willReturn(aResponse().withBody("{\"event\":\"a\"}")));
    }

    @Test
    void testA() {
        ResponseEntity<String> response = restTemplate.exchange("a", HttpMethod.GET, RequestEntity.EMPTY, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("a");
    }

}