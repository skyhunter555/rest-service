package com.example.restservice;

import lombok.Data;

/**
 * Greeting entity
 *
 * @author Skyhunter
 * @date 09.02.2021
 */
@Data
public class Greeting {

    private final long id;
    private final String content;

}
