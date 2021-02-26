package com.example.restservice.services;

/**
 * TestMessageService interface
 *
 * @author Skyhunter
 * @date 26.02.2021
 */
import com.example.restservice.entities.TestMessage;

import java.util.List;

public interface TestMessageService {

    List<TestMessage> findAll();

    TestMessage findById(Integer id);

    Integer create(TestMessage resource);

    void update(Integer id, TestMessage resource);

    void deleteById(Integer id);
}
