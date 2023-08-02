package com.adriabt.usersservice.dtos;

import com.adriabt.usersservice.enums.MaritalStatus;
import lombok.Data;

@Data
public class SearchCaractures {
    private String firstname;
    private String lastname;
    private String email;
    private String cin;
    private MaritalStatus maritalStatus;

}
