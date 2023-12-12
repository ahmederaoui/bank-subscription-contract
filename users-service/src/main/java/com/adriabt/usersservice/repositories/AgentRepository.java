package com.adriabt.usersservice.repositories;

import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,String> {
    Optional<Agent> findAgentByEmail(String email);

}
