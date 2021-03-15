package com.example.restservice.entrypoints.http;

import com.example.restservice.entities.TestMessage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TestMessageController test
 *
 * @author Skyhunter
 * @date 15.03.2021
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMessageControllerTest {

    private static final Integer TEST_MESSAGE_ID = 1;
    private static final String TEST_MESSAGE_EXPECTED_CONTENT = "test111";
    private static final String TEST_MESSAGE_CREATED_CONTENT = "test_created";
    private static final String TEST_MESSAGE_UPDATED_CONTENT = "test_updated";

    final TestMessage createdTestMessage = TestMessage.builder().content(TEST_MESSAGE_CREATED_CONTENT).build();
    final TestMessage updatedTestMessage = TestMessage.builder().content(TEST_MESSAGE_UPDATED_CONTENT).build();

    @LocalServerPort
    private int port;

    @Autowired
    private TestMessageController testMessageController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(0)
    void contextLoads() {
        assertThat(testMessageController).isNotNull();
    }


    @Test
    @Order(1)
    void findAll() {
        ResponseEntity<TestMessage[]> response = restTemplate
                .getForEntity(String.format("http://localhost:%s/test-message/api/v1", port), TestMessage[].class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        TestMessage[] body = response.getBody();
        assertThat(body.length).isEqualTo(1);
        assertThat(body[0].getContent()).isEqualTo(TEST_MESSAGE_EXPECTED_CONTENT);
    }

    @Test
    @Order(2)
    void create() {

        ResponseEntity<Integer> response = restTemplate
                .postForEntity(String.format("http://localhost:%s/test-message/api/v1", port), new HttpEntity<>(createdTestMessage), Integer.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        Integer body = response.getBody();
        assertThat(body).isGreaterThan(TEST_MESSAGE_ID);
    }

    @Test
    @Order(3)
    void update() {
        ResponseEntity<?> response = restTemplate
                .exchange(String.format("http://localhost:%s/test-message/api/v1/1", port),
                        HttpMethod.PUT, new HttpEntity<>(updatedTestMessage), Object.class);
        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(4)
    void findById() {

        ResponseEntity<TestMessage> response = restTemplate
                .getForEntity(String.format("http://localhost:%s/test-message/api/v1/1", port), TestMessage.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        TestMessage body = response.getBody();
        assertThat(body.getId()).isEqualTo(TEST_MESSAGE_ID);
    }

    @Test
    @Order(5)
    void deleteById() {

        ResponseEntity<?> response = restTemplate
                .exchange(String.format("http://localhost:%s/test-message/api/v1/1", port),
                        HttpMethod.DELETE, new HttpEntity<>(null), Object.class);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}