package com.bankmandiri.assesment.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp birthDate;
    private Double basicSalary;
    private String status;
    private String group;
    private String description;

    public UserDto(Long id, String userName, String firstName, String lastName, String email, Timestamp birthDate, Double basicSalary, String status, String group, String description) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.basicSalary = basicSalary;
        this.status = status;
        this.group = group;
        this.description = description;
    }
}
