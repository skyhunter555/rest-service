package com.example.restservice.services;

import com.example.restservice.entities.TestMessage;
import com.example.restservice.exceptions.TestMessageNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * TestMessageService test
 *
 * @author Skyhunter
 * @date 15.03.2021
 */
@SpringBootTest
@Transactional
public class TestMessageServiceTest {

    private static final Integer TEST_MESSAGE_ID = 1;
    private static final String TEST_MESSAGE_CONTENT = "testcontent_updated";

    @Autowired
    private TestMessageService testMessageService;

    @Test
    void contextTest() {
        assertThat(testMessageService, notNullValue());
    }

    @Test
    void findAll() {
        List<TestMessage> testMessages = testMessageService.findAll();
        assertThat(testMessages, not(empty()));
    }

    @Test
    void findById() {
        TestMessage findUser = testMessageService.findById(TEST_MESSAGE_ID);
        assertThat(findUser, notNullValue());
    }

    @Test
    void createAndDeleteById() {
        TestMessage testMessage = TestMessage.builder().id(TEST_MESSAGE_ID).content(TEST_MESSAGE_CONTENT).build();
        Integer createdMessageId = testMessageService.create(testMessage);
        TestMessage createdTestMessage = testMessageService.findById(createdMessageId);
        assertThat(createdTestMessage, notNullValue());
        testMessageService.deleteById(createdMessageId);
        Assertions.assertThrows(TestMessageNotFoundException.class, () -> testMessageService.findById(createdMessageId));
    }

    @Test
    void updateById() {
        TestMessage notUpdatedTestMessage = testMessageService.findById(TEST_MESSAGE_ID);
        assertThat(notUpdatedTestMessage, notNullValue());
        notUpdatedTestMessage.setContent(TEST_MESSAGE_CONTENT);
        testMessageService.update(TEST_MESSAGE_ID, notUpdatedTestMessage);

        TestMessage updatedTestMessage = testMessageService.findById(TEST_MESSAGE_ID);
        assertThat(updatedTestMessage.getContent(), equalTo(TEST_MESSAGE_CONTENT));
    }


}