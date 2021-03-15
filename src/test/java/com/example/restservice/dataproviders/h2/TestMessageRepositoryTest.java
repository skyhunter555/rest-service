package com.example.restservice.dataproviders.h2;

import com.example.restservice.entities.TestMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * TestMessageRepository test
 *
 * @author Skyhunter
 * @date 15.03.2021
 */
@SpringBootTest
class TestMessageRepositoryTest {

    private static final Integer TEST_MESSAGE_ID = 1;

    @Autowired
    private TestMessageRepository repository;

    @Test
    void contextTest() {
        assertThat(repository, notNullValue());
    }

    @Test
    void findAll() {
        List<TestMessage> testMessages = repository.findAll();
        Assertions.assertNotNull(testMessages);
        Assertions.assertEquals(1, testMessages.size());
    }

    @Test
    void findByName() {
        Assertions.assertTrue(repository.findById(TEST_MESSAGE_ID).isPresent());
    }
}