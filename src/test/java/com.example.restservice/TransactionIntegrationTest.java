package com.example.restservice;

import com.example.restservice.dataproviders.postgres.TestMessageRepository;
import com.example.restservice.entities.TestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * TransactionIntegration test
 *
 * @author Skyhunter
 * @date 11.03.2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionIntegrationTest {

    private static Logger LOG = LogManager.getLogger(TransactionIntegrationTest.class);

    @Autowired
    private TestMessageRepository testMessageRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    public void givenTwoMessageWhenContentIsDuplicateThenShouldRollback() {

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        try {
            transactionTemplate.execute(status -> {

                TestMessage testMessage1 = new TestMessage();
                testMessage1.setContent("testcontent");

                TestMessage testMessage2 = new TestMessage();
                testMessage2.setContent("testcontent");

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

}
