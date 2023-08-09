package com.adriabt.securityservice.services;

import com.adriabt.securityservice.models.Agent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "USERS-SERVICE")
public interface AgentService {
    @GetMapping(path = "/api/agents/email/{email}")
    Optional<Agent> getAgentByEmail(@PathVariable String email);
}
