package ru.diasoft.digitalq.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.digitalq.domain.*;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;
import ru.diasoft.digitalq.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SmsVerificationServiceTest {

    @Mock
    private SmsVerificationRepository repository;
    @Mock
    private SmsVerificationCreatedPublishGateway messagingGateway;

    private SmsVerificationPrimaryService service;

    private final String PHONE_NUMBER = "79212223344";
    private final String VALID_SECRET_CODE = "555";
    private final String INVALID_SECRET_CODE = "777";
    private final String GUID = UUID.randomUUID().toString();
    private final String STATUS = "OK";

    @Before
    public void init(){
        service = new SmsVerificationPrimaryService(repository, messagingGateway);

        SmsVerification smsVerification = SmsVerification.builder()
            .processGuid(GUID)
            .phoneNumber(PHONE_NUMBER)
            .secretCode(VALID_SECRET_CODE)
            .status(STATUS)
            .build();

        when(repository.findBySecretCodeAndProcessGuidAndStatus(VALID_SECRET_CODE, GUID, STATUS))
            .thenReturn(Optional.of(smsVerification));
        when(repository.findBySecretCodeAndProcessGuidAndStatus(INVALID_SECRET_CODE, GUID, STATUS))
            .thenReturn(Optional.empty());
    }

    @Test
    public void testDsSmsVerificationCheck_Valid() {
        ResponseEntity<SmsVerificationCheckResponse> response = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID, VALID_SECRET_CODE));
        Assert.assertSame(response.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(Objects.requireNonNull(response.getBody()).getCheckResult());
    }

    @Test
    public void testDsSmsVerificationCheck_Invalid() {
        ResponseEntity<SmsVerificationCheckResponse> response = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID, INVALID_SECRET_CODE));
        Assert.assertFalse(Objects.requireNonNull(response.getBody()).getCheckResult());
    }

    @Test
    public void testDsSmsVerificationCreate() {
        ResponseEntity<SmsVerificationPostResponse> response = service.dsSmsVerificationCreate(new SmsVerificationPostRequest(PHONE_NUMBER));
        Assert.assertSame(response.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotNull(Objects.requireNonNull(response.getBody()).getProcessGUID());
    }

}
