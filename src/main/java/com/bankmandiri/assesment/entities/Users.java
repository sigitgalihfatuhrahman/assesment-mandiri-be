package com.bankmandiri.assesment.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "birth_date")
    @JsonProperty("birth_date")
    private Timestamp birthDate;

    @Column(name = "basic_salary")
    @JsonProperty("basic_salary")
    private Double basicSalary;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Column(name = "group_name")
    @JsonProperty("group_name")
    private String groupName;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

}
