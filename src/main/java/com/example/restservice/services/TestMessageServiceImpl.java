package com.example.restservice.services;

import com.example.restservice.entities.TestMessage;
import com.example.restservice.exceptions.TestMessageNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TestMessageService implementation
 *
 * @author Skyhunter
 * @date 26.02.2021
 */
@Service
public class TestMessageServiceImpl implements TestMessageService {

    private final List<TestMessage> testMessageList = new ArrayList<>();
    private final AtomicInteger messageCounter = new AtomicInteger();

    @Override
    public List<TestMessage> findAll() {
        return testMessageList;
    }

    @Override
    public TestMessage findById(Integer id) {
        Optional<TestMessage> optionalTestMessage = testMessageList.stream().filter(foo -> foo.getId().equals(id)).findFirst();
        if (!optionalTestMessage.isPresent()) {
            throw new TestMessageNotFoundException("Message not found by id");
        }
        return optionalTestMessage.get();
    }

    @Override
    public Integer create(TestMessage testMessage) {
        testMessage.setId(messageCounter.incrementAndGet());
        testMessageList.add(testMessage);
        return testMessage.getId();
    }

    @Override
    public void update(Integer id, TestMessage testMessage) {
        updateFields(testMessage, findById(id));
    }

    private void updateFields(TestMessage source, TestMessage target) {
        target.setContent(source.getContent());
    }

    @Override
    public void deleteById(Integer id) {
        testMessageList.remove(findById(id));
    }
}
