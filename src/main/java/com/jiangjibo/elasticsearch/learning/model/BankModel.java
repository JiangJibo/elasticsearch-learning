package com.jiangjibo.elasticsearch.learning.model;

import lombok.Data;

/**
 * @author wb-jjb318191
 * @create 2020-07-16 11:13
 */
@Data
public class BankModel {

    private Integer account_number;

    private Integer balance;

    private String firstname;

    private String lastname;

    private Integer age;

    private String gender;

    private String address;

    private String employer;

    private String email;

    private String city;

    private String state;

}
