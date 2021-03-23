package com.example.restservice.dataproviders.h2;

import com.example.restservice.entities.TestMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * TestMessageRepository interface for DB
 *
 * @author Skyhunter
 * @date 02.03.2021
 */
public interface TestMessageRepository extends CrudRepository<TestMessage, Integer> {
    List<TestMessage> findAll();
}
