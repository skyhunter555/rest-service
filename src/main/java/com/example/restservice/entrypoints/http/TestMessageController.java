package com.example.restservice.entrypoints.http;

import com.example.restservice.entities.TestMessage;
import com.example.restservice.exceptions.TestMessageNotFoundException;
import com.example.restservice.kafka.MessageGateway;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * Rest controller
 *
 * @author Skyhunter
 * @date 26.02.2021
 */
@RestController
@RequestMapping("/test-message/api/v1")
@Api(value = "test-message")
public class TestMessageController {

    private static Logger LOG = LogManager.getLogger(TestMessageController.class);

    private final MessageGateway messageGateway;
    private Integer messageId = 0;

    public TestMessageController(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    @GetMapping(value = "/{message}")
    @ApiOperation(value = "Send test message to kafka", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> sendTestMessageToKafka(
            @PathVariable("message") String messageContent
    ) {
        messageId++;
        TestMessage testMessage = TestMessage.builder().id(messageId).content(messageContent).build();
        LOG.info("Start direct testMessage to kafka");
        messageGateway.directMessage(testMessage);
        return new ResponseEntity<>(String.format("Test message %s with id %s sended to kafka", messageContent, messageId), HttpStatus.OK);
    }

    @ExceptionHandler({TestMessageNotFoundException.class})
    public void handleResourceNotFoundException(HttpServletResponse response) {
        response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
    }

}

