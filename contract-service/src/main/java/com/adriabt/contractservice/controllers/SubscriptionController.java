package com.adriabt.contractservice.controllers;

import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.services.ISubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final ISubscriptionService subscriptionService;
    @PostMapping("/create")
    public ResponseEntity<?> addSubscription(@RequestBody Subscription subscription){
        try {
            return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateSubscription(@RequestBody Subscription subscription){
        try {
            return ResponseEntity.ok(subscriptionService.updateSubscription(subscription));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> getSubscriptions(@RequestParam(value = "agency",defaultValue = "") String agency,
                                              @RequestParam(value = "contractStatus",defaultValue = "") String contractStatus,
                                              @RequestParam(value = "agency",defaultValue = "") String contractType,
                                              @RequestParam(value = "contractNum",defaultValue = "") Long contractNum,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size){
        try {
            return ResponseEntity.ok(subscriptionService.getSubscriptions( agency,  contractStatus,  contractType,  contractNum, page, size));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/cancelOrTerminate/{subscriptionId}")
    public ResponseEntity<?> cancelOrTerminateSubscription(@PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(subscriptionService.cancelOrTerminateSubscription(subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
