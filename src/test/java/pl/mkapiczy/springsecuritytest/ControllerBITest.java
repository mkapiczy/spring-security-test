package pl.mkapiczy.springsecuritytest;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 11111)
class ControllerBITest {
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        WireMock.reset();
        WireMock.stubFor(post(urlEqualTo("events-b")).willReturn(aResponse().withBody("{\"event\":\"b\"}")));
    }

    @Test
    void testA() {
        ResponseEntity<String> response = restTemplate.exchange("b", HttpMethod.GET, RequestEntity.EMPTY, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("b");
    }

}