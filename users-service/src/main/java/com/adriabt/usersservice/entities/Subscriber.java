package com.adriabt.usersservice.entities;

import com.adriabt.usersservice.enums.MaritalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber extends AppUser{
    private String nationality;
    private String cin;
    private Date birthday;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private List<String> attachmentIds;
}
