package com.example.restservice.kafka;

import com.example.restservice.entities.TestMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * MessageGateway to kafka
 *
 * @author Skyhunter
 * @date 31.03.2021
 */
@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Topics.DEMO)
    void directMessage(TestMessage testMessage);
}
