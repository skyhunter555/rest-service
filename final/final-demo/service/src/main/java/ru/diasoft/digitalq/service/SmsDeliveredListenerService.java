package ru.diasoft.digitalq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.digitalq.model.SmsDeliveredMessage;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;
import ru.diasoft.digitalq.smsverificationdelivered.subscribe.SmsVerificationDeliveredSubscribeListenerService;

/**
 * SmsDeliveredListenerService
 *
 * @author Skyhunter
 * @date 16.04.2021
 */
@RequiredArgsConstructor
@Service
@Primary
public class SmsDeliveredListenerService implements SmsVerificationDeliveredSubscribeListenerService {

    private final SmsVerificationRepository repository;

    @Override
    public void smsVerificationDelivered(SmsDeliveredMessage msg) {
        repository.updateStatusByProcessGuid("OK",msg.getGuid());
    }
}

