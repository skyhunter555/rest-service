package com.example.restservice.entrypoints.http;

import com.example.restservice.entities.TestMessage;
import com.example.restservice.services.GetUserDetailsService;
import com.example.restservice.services.TestMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
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
    private static final String TEST_MESSAGE_CREATED_CONTENT = "test_created";
    private static final String TEST_MESSAGE_UPDATED_CONTENT = "test_updated";

    private final TestMessage testMessage = TestMessage.builder().id(TEST_MESSAGE_ID).content(TEST_MESSAGE_EXPECTED_CONTENT).build();
    private final TestMessage createdTestMessage = TestMessage.builder().content(TEST_MESSAGE_CREATED_CONTENT).build();
    private final TestMessage updatedTestMessage = TestMessage.builder().content(TEST_MESSAGE_UPDATED_CONTENT).build();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TestMessageService testMessageService;

    @MockBean
    private GetUserDetailsService getUserDetailsService;

    @Test
    @Order(1)
    void findAll() throws Exception
    {
        List<TestMessage> mockTestMessages = new ArrayList<>();
        mockTestMessages.add(testMessage);
        Mockito.when(testMessageService.findAll()).thenReturn(mockTestMessages);

        mvc.perform( MockMvcRequestBuilders
                .get("/test-message/api/v1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(TEST_MESSAGE_ID));
    }

    @Test
    @Order(2)
    void create() throws Exception {

        Mockito.when(testMessageService.create(createdTestMessage)).thenReturn(TEST_MESSAGE_ID);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(createdTestMessage);

        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .post("/test-message/api/v1")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(TEST_MESSAGE_ID.toString(), equalTo(content));

    }


    @Test
    @Order(3)
    void update() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(updatedTestMessage);

        mvc.perform( MockMvcRequestBuilders
                .put("/test-message/api/v1/1")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @Order(4)
    void findById() throws Exception {

        Mockito.when(testMessageService.findById(TEST_MESSAGE_ID)).thenReturn(testMessage);

        mvc.perform( MockMvcRequestBuilders
                .get("/test-message/api/v1/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(TEST_MESSAGE_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(TEST_MESSAGE_EXPECTED_CONTENT));
    }

    @Test
    @Order(5)
    void deleteById() throws Exception {

        mvc.perform( MockMvcRequestBuilders
                .delete("/test-message/api/v1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

}