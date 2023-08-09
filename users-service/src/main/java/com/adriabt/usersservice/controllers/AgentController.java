package com.adriabt.usersservice.controllers;

import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.services.IAgentService;
import com.adriabt.usersservice.services.MFATokenManager;
import com.adriabt.usersservice.services.impl.DefaultMFATokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {
    private final IAgentService agentService;
    private final MFATokenManager mfaTokenManager;

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAgentByEmail(@PathVariable String email) {
        try {
            System.out.println(agentService.findAgentByEmail(email));
            return ResponseEntity.ok(agentService.findAgentByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/agent")
    public ResponseEntity<?> addAgent(@RequestBody Agent agent){
        try {
            return ResponseEntity.ok(agentService.createAgent(agent));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/agent")
    public ResponseEntity<?> updateAgent(@RequestBody Agent agent){
        try {
            return ResponseEntity.ok(agentService.updateAgent(agent));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/verify")
    public boolean verify(@RequestParam(value = "code",defaultValue = "") String code,
                          @RequestParam(value = "secret",defaultValue = "") String secret){
        return mfaTokenManager.verifyTotp(code,secret);
    }
}
