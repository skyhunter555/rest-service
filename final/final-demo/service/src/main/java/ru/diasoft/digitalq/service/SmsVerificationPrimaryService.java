package ru.diasoft.digitalq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.diasoft.digitalq.domain.*;
import ru.diasoft.digitalq.model.SmsVerificationMessage;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;
import ru.diasoft.digitalq.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/**
 * SmsVerificationPrimaryService
 *
 * @author Skyhunter
 * @date 16.04.2021
 */
@RequiredArgsConstructor
@Service
@Primary
public class SmsVerificationPrimaryService implements SmsVerificationService {

    private final SmsVerificationRepository smsVerificationRepository;
    private final SmsVerificationCreatedPublishGateway messagingGateway;

    @Override
    public ResponseEntity<SmsVerificationCheckResponse> dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        Optional<SmsVerification> result =
                smsVerificationRepository.findBySecretCodeAndProcessGuidAndStatus(smsVerificationCheckRequest.getCode(),
                smsVerificationCheckRequest.getProcessGUID(), "OK");
        return ResponseEntity.status(HttpStatus.OK).body(new SmsVerificationCheckResponse(result.isPresent()));
    }

    @Override
    public ResponseEntity<SmsVerificationPostResponse> dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        String guid = UUID.randomUUID().toString();
        String code = String.format("%04d", new Random().nextInt(10000));
        SmsVerification smsVerification = SmsVerification.builder()
                .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                .processGuid(guid)
                .secretCode(code)
                .status("WAITING")
                .build();
        smsVerificationRepository.save(smsVerification);
        messagingGateway.smsVerificationCreated(SmsVerificationMessage.builder().guid(guid).code(code).phoneNumber(smsVerificationPostRequest.getPhoneNumber()).build());
        return ResponseEntity.status(HttpStatus.CREATED).body(new SmsVerificationPostResponse(guid));
    }
}
