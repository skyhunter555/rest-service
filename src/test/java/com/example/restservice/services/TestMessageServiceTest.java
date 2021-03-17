package com.example.restservice.services;

import com.example.restservice.dataproviders.h2.TestMessageRepository;
import com.example.restservice.entities.TestMessage;
import com.example.restservice.exceptions.TestMessageNotFoundException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final Integer TEST_MESSAGE_ID = 1;
    private final String TEST_MESSAGE_CONTENT = "testcontent_updated";
    private final TestMessage testMessage = TestMessage.builder().id(TEST_MESSAGE_ID).content(TEST_MESSAGE_CONTENT).build();

    @Autowired
    private TestMessageService testMessageService;

    @MockBean
    private TestMessageRepository testMessageRepository;

    @Test
    void contextTest() {
        assertThat(testMessageService, notNullValue());
    }

    @Test
    void findAll() {
        List<TestMessage> mockTestMessages = new ArrayList<>();
        mockTestMessages.add(testMessage);
        Mockito.when(testMessageRepository.findAll()).thenReturn(mockTestMessages);
        List<TestMessage> testMessages = testMessageService.findAll();
        MatcherAssert.assertThat(testMessages, not(empty()));
    }

    @Test
    void findById() {
        Mockito.when(testMessageRepository.findById(TEST_MESSAGE_ID)).thenReturn(Optional.ofNullable(testMessage));

        TestMessage findTestMessage = testMessageService.findById(TEST_MESSAGE_ID);
        assertThat(findTestMessage, notNullValue());
    }

    @Test
    void createAndDeleteById() {
        Mockito.when(testMessageRepository.save(testMessage)).thenReturn(testMessage);
        Mockito.when(testMessageRepository.findById(TEST_MESSAGE_ID)).thenReturn(Optional.ofNullable(testMessage));

        TestMessage testMessage = TestMessage.builder().id(TEST_MESSAGE_ID).content(TEST_MESSAGE_CONTENT).build();
        Integer createdMessageId = testMessageService.create(testMessage);
        TestMessage createdTestMessage = testMessageService.findById(createdMessageId);
        assertThat(createdTestMessage, notNullValue());
        testMessageService.deleteById(createdMessageId);
    }

    @Test
    void updateById() {
        Mockito.when(testMessageRepository.findById(TEST_MESSAGE_ID)).thenReturn(Optional.ofNullable(testMessage));

        TestMessage notUpdatedTestMessage = testMessageService.findById(TEST_MESSAGE_ID);
        assertThat(notUpdatedTestMessage, notNullValue());
        notUpdatedTestMessage.setContent(TEST_MESSAGE_CONTENT);
        testMessageService.update(TEST_MESSAGE_ID, notUpdatedTestMessage);

        TestMessage updatedTestMessage = testMessageService.findById(TEST_MESSAGE_ID);
        assertThat(updatedTestMessage.getContent(), equalTo(TEST_MESSAGE_CONTENT));
    }


}