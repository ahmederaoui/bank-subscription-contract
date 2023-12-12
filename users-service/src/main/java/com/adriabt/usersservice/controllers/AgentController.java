package com.adriabt.usersservice.controllers;

import com.adriabt.usersservice.dtos.VerificationRequest;
import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.services.IAgentService;
import com.adriabt.usersservice.services.impl.DefaultMFATokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {
    private final IAgentService agentService;
    private final DefaultMFATokenManager mfaTokenManager;

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAgentByEmail(@PathVariable String email) {
        try {
            System.out.println(agentService.findAgentByEmail(email));
            return ResponseEntity.ok(agentService.findAgentByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/qrcode/{email}")
    public ResponseEntity<?> getQrCode(@PathVariable String email) {
        try {
            return ResponseEntity.ok(agentService.getQrCode(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> addAgent(@RequestBody Agent agent){
        try {
            return ResponseEntity.ok(agentService.createAgent(agent));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAgent(@RequestBody Agent agent){
        try {
            return ResponseEntity.ok(agentService.updateAgent(agent));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping ("/verify")
    public ResponseEntity<?> verify(@RequestBody VerificationRequest verificationRequest){
        try {
            return ResponseEntity.ok(mfaTokenManager.isOtpValid(verificationRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping
    public String getString(@RequestBody String ahmed){
        return ahmed;
    }
}
