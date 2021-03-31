package com.example.restservice.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * ConsumerChannel
 *
 * @author Skyhunter
 * @date 31.03.2021
 */
public interface ConsumerChannel {

    @Input(Topics.DEMO)
    SubscribableChannel demo();
}