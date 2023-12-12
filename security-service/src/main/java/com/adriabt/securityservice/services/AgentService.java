package com.adriabt.securityservice.services;

import com.adriabt.securityservice.dtos.VerificationRequest;
import com.adriabt.securityservice.models.Agent;
import jakarta.ws.rs.HeaderParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "USERS-SERVICE")
public interface AgentService {
    @GetMapping(path = "/api/agents/email/{email}")
    Agent getAgentByEmail(@PathVariable String email, @RequestHeader("Authorization")String token);
    @PostMapping(path = "/api/agents/verify")
    boolean verifyTotp(@RequestBody VerificationRequest verificationRequest);
}
