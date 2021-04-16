package ru.diasoft.digitalq.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.digitalq.domain.SmsVerification;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SmsVerificationRepositoryTest {

    @Autowired
    private SmsVerificationRepository repository;
    private final String PHONE_NUMBER = "79212223344";
    private final String SECRET_CODE = "555";
    @Test
    public void smsVerificationCreateTest(){
        SmsVerification smsVerification = SmsVerification.builder()
            .processGuid(UUID.randomUUID().toString())
            .phoneNumber(PHONE_NUMBER)
            .secretCode(SECRET_CODE)
            .status("WAIT")
            .build();

        SmsVerification createdEntity = repository.save(smsVerification);
        Assert.assertNotNull(createdEntity.getVerificationId());
    }

    @Test
    public void findBySecretCodeAndProcessGuidAndStatusTest(){
        String processGuid = UUID.randomUUID().toString();
        String status = "WAIT";

        SmsVerification smsVerification = SmsVerification.builder()
            .processGuid(processGuid)
            .phoneNumber(PHONE_NUMBER)
            .secretCode(SECRET_CODE)
            .status(status)
            .build();

        SmsVerification createdEntity = repository.save(smsVerification);
        Assert.assertNotNull(repository.findBySecretCodeAndProcessGuidAndStatus(SECRET_CODE, processGuid, status));
    }

    @Test
    public void updateStatusByProcessingGuidTest() {
        String guid = UUID.randomUUID().toString();
        String status = "WAITING";

        SmsVerification smsVerification =
                SmsVerification.builder()
                        .processGuid(guid)
                        .phoneNumber(PHONE_NUMBER)
                        .secretCode(SECRET_CODE)
                        .status(status)
                        .build();
        SmsVerification createdEntity = repository.save(smsVerification);
        int count = repository.updateStatusByProcessGuid("OK", guid);
        Assert.assertEquals(count, 1);

        Optional<SmsVerification> updatedEntity = repository.findById(createdEntity.getVerificationId());
        Assert.assertTrue(updatedEntity.isPresent());

        Assert.assertEquals(updatedEntity.orElse(SmsVerification.builder().build()).getStatus(), "OK");
    }
}
