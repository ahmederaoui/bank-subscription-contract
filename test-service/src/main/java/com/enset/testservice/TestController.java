package com.enset.testservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test/name")
    public String testFunction(){
        return "${myName}"+" test";
    }
}
