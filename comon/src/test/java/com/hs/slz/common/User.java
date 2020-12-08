package com.hs.slz.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private String name;
    private Integer age;
    private Double score;
}
