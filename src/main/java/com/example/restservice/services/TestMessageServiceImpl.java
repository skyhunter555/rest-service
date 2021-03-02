package com.example.restservice.services;

import com.example.restservice.dataproviders.h2.TestMessageRepository;
import com.example.restservice.entities.TestMessage;
import com.example.restservice.exceptions.TestMessageNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * TestMessageService implementation
 *
 * @author Skyhunter
 * @date 02.03.2021
 */
@Service
public class TestMessageServiceImpl implements TestMessageService {

    private final TestMessageRepository testMessageRepository;

    public TestMessageServiceImpl(TestMessageRepository testMessageRepository) {
        this.testMessageRepository = testMessageRepository;
    }

    @Override
    public List<TestMessage> findAll() {
        return testMessageRepository.findAll();
    }

    @Override
    public TestMessage findById(Integer id) {
        Optional<TestMessage> optionalTestMessage = testMessageRepository.findById(id);
        if (!optionalTestMessage.isPresent()) {
            throw new TestMessageNotFoundException("Message not found by id");
        }
        return optionalTestMessage.get();
    }

    @Override
    public Integer create(TestMessage testMessage) {
        TestMessage newTestMessage = testMessageRepository.save(testMessage);
        return newTestMessage.getId();
    }

    @Override
    public void update(Integer id, TestMessage testMessage) {
        TestMessage messageForUpdate = findById(id);
        messageForUpdate.setContent(testMessage.getContent());
        testMessageRepository.save(messageForUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        testMessageRepository.delete(findById(id));
    }
}
