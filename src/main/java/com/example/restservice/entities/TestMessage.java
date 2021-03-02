package com.example.restservice.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
/**
 * TestMessage entity
 *
 * @author Skyhunter
 * @date 02.03.2021
 */
@Entity
@Table(name = "test_messages")
@Data
public class TestMessage {

    @Id
    @GeneratedValue(generator = "test_messages_seq")
    @SequenceGenerator(name = "test_messages_seq", sequenceName = "test_messages_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

}
