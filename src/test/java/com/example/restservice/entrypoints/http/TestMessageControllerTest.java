package com.example.restservice.entrypoints.http;

import com.example.restservice.entities.TestMessage;
import com.example.restservice.kafka.MessageGateway;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TestMessageController test
 *
 * @author Skyhunter
 * @date 15.03.2021
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(TestMessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestMessageControllerTest {

    private static final Integer TEST_MESSAGE_ID = 1;
    private static final String TEST_MESSAGE_EXPECTED_CONTENT = "test111";

    private final TestMessage testMessage = TestMessage.builder().id(TEST_MESSAGE_ID).content(TEST_MESSAGE_EXPECTED_CONTENT).build();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageGateway messageGateway;

    @Test
    void findAll() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/test-message/api/v1/test111")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}