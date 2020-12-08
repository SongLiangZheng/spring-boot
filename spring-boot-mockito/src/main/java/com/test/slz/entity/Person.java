package com.test.slz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Person implements Serializable{
    private Integer sid;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private BigDecimal salary;
    private double bonus;

}