package com.adriabt.contractservice.controllers;

import com.adriabt.contractservice.dtos.AttachmentDTO;
import com.adriabt.contractservice.entities.Subscription;
import com.adriabt.contractservice.enums.ClientSegment;
import com.adriabt.contractservice.enums.ContractStatus;
import com.adriabt.contractservice.enums.ContractType;
import com.adriabt.contractservice.services.ISubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
                                              @RequestParam(value = "contractStatus",defaultValue = "") ContractStatus contractStatus,
                                              @RequestParam(value = "contractType",defaultValue = "") ContractType contractType,
                                              @RequestParam(value = "clientSegment",defaultValue = "") ClientSegment clientSegment,
                                              @RequestParam(value = "id",defaultValue = "") String id,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size){
        try {
            return ResponseEntity.ok(subscriptionService.getSubscriptions( agency,  contractStatus,  contractType, clientSegment,id,page, size));
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
    @PostMapping("/attach")
    public ResponseEntity<?> subscriptionAttachment(@RequestBody AttachmentDTO attachmentDTO){
        try {
            subscriptionService.subscriptionAttachment(attachmentDTO);
            return new ResponseEntity<>("Attachment done", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/detach")
    public ResponseEntity<?> subscriptionDetachment(@RequestBody AttachmentDTO attachmentDTO){
        try {
            subscriptionService.subscriptionDetachment(attachmentDTO);
            return new ResponseEntity<>("Attachment done", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search/{subscriptionId}")
    public ResponseEntity<?> getSubscriptionsById(@PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(subscriptionService.getSubscriptionById(subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
