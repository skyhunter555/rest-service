package com.example.restservice;

import com.example.restservice.dataproviders.h2.TestMessageRepository;
import com.example.restservice.entities.TestMessage;
import com.example.restservice.services.TestMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * TransactionIntegration test
 *
 * @author Skyhunter
 * @date 15.03.2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionIntegrationTest {

    private static Logger LOG = LogManager.getLogger(TransactionIntegrationTest.class);

    @MockBean
    private TestMessageService testMessageService;

    @Autowired
    private TestMessageRepository testMessageRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    public void givenTwoMessageWhenContentIsDuplicateThenShouldRollback() {

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        try {
            transactionTemplate.execute(status -> {

                TestMessage testMessage1 = TestMessage.builder().content("testcontent").build();
                TestMessage testMessage2 = TestMessage.builder().content("testcontent").build();

                testMessageRepository.save(testMessage1);
                testMessageRepository.save(testMessage2);

                return "Ref-1";
            });

        } catch (Exception exception) {
            LOG.error("Rollback");
            exception.printStackTrace();
            //ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности "test_messages_content_key"
        }

    }


    @Test
    public void findAll() throws Exception {

        TestMessage testMessage = TestMessage.builder().content("testcontent").build();

        when(testMessageService.findAll()).thenReturn(Collections.singletonList(testMessage));

        List<TestMessage> testMessages = testMessageService.findAll();

        Assertions.assertEquals(1, testMessages.size());
    }

}
