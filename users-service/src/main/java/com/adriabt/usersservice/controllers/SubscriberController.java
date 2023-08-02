package com.adriabt.usersservice.controllers;

import com.adriabt.usersservice.entities.Subscriber;
import com.adriabt.usersservice.enums.MaritalStatus;
import com.adriabt.usersservice.services.ISubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscribers")
@RequiredArgsConstructor
public class SubscriberController {
    private final ISubscriberService subscriberService;

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAgentByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(subscriberService.findSubscriberByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> getAllSubscribers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size){
        try {
            return ResponseEntity.ok(subscriberService.findAllSubscribersByKeyword(page,size));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteSubscriber(@PathVariable String email){
        try {
            return ResponseEntity.ok(subscriberService.deleteSubscriber(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/subscriber")
    public ResponseEntity<?> addSubscriber(@RequestBody Subscriber subscriber){
        try {
            return ResponseEntity.ok(subscriberService.createSubscriber(subscriber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping ("/subscriber")
    public ResponseEntity<?> updateSubscriber(@RequestBody Subscriber subscriber){
        try {
            return ResponseEntity.ok(subscriberService.updateSubscriber(subscriber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
