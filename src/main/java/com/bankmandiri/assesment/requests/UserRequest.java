package com.bankmandiri.assesment.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;


@Data
@RequiredArgsConstructor
public class UserRequest {
    @NonNull
    private String userName;
    @NonNull
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp birthDay;
    private Double basicSalary;
    private String status;
    private String groupName;
    private String description;
}
