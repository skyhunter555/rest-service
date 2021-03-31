package com.example.restservice.kafka;

import com.example.restservice.entities.TestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

/**
 * ChanelListener
 *
 * @author Skyhunter
 * @date 31.03.2021
 */
@Configuration
public class ChanelListener {

    private static Logger LOG = LogManager.getLogger(ChanelListener.class);

    @StreamListener(Topics.DEMO)
    public void demo(TestMessage message) {

        LOG.info("Received test message: " + message);
    }

}