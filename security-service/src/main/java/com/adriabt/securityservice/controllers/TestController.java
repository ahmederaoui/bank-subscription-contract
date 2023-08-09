package com.adriabt.securityservice.controllers;

import com.adriabt.securityservice.dtos.AuthDto;
import com.adriabt.securityservice.services.AgentService;
import com.adriabt.securityservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final AgentService agentService;
    private final AuthService authService;
    @PostMapping("/v1/token")
    public Object getAgent(@RequestBody AuthDto authDto){
        try {
            return authService.getJwtToken(authDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    /*@GetMapping
    public String getString(){
        return "ahmed eraoui";
    }*/
}
