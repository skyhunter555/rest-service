package com.example.restservice.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * ProducerChannel
 *
 * @author Skyhunter
 * @date 31.03.2021
 */
public interface ProducerChannel {

    @Output(Topics.DEMO)
    MessageChannel testMessage();
}
