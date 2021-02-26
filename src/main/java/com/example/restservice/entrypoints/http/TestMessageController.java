package com.example.restservice.entrypoints.http;

import com.example.restservice.LogExecutionTime;
import com.example.restservice.entities.TestMessage;
import com.example.restservice.exceptions.TestMessageNotFoundException;
import com.example.restservice.services.TestMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

    private final TestMessageService testMessageService;

    public TestMessageController(TestMessageService testMessageService) {
        this.testMessageService = testMessageService;
    }

    @GetMapping
    @ApiOperation(value = "Get all messages", produces = "application/json")
    public List<TestMessage> findAll() {
        return testMessageService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get message by id", produces = "application/json")
    @LogExecutionTime
    public TestMessage findById(
            @PathVariable("id") Integer id
    ) {
        return testMessageService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create message", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @LogExecutionTime
    public Integer create(
            @RequestBody TestMessage testMessage
    ) {
        return testMessageService.create(testMessage);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update message by id", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable("id") Integer id,
            @RequestBody TestMessage testMessage
    ) {
        testMessageService.update(id, testMessage);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove message by id", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("id") Integer id
    ) {
        testMessageService.deleteById(id);
    }

    @ExceptionHandler({TestMessageNotFoundException.class})
    public void handleResourceNotFoundException(HttpServletResponse response) {
        response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
    }

}

