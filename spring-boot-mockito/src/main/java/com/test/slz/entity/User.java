package com.test.slz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;

    public User() {

    }
}
