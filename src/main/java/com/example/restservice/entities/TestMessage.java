package com.example.restservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TestMessage entity
 *
 * @author Skyhunter
 * @date 02.03.2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestMessage {
    private Integer id;
    private String content;
}
