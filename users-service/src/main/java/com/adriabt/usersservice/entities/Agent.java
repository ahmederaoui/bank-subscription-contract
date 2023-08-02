package com.adriabt.usersservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Agent extends AppUser{
    private String agency;
    private Date hiringDate;
    @JsonIgnore
    private List<String> contracts=new ArrayList<>();
}
