package com.example.demo;


import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
public class Person {
    @Id
    Integer id;
    String lastname;
    String surname;
}