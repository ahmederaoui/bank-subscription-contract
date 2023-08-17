package com.adriabt.contractservice.controllers;

import com.adriabt.contractservice.entities.SignatureProfile;
import com.adriabt.contractservice.services.ISignatureProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signatureProfiles")
@RequiredArgsConstructor
public class SignatureProfileController {
    private final ISignatureProfileService signatureProfileService;
    @PostMapping("/create/{subscriptionId}")
    public ResponseEntity<?> addSignatureProfileToSubscription(@RequestBody SignatureProfile signatureProfile,@PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(signatureProfileService.createSignatureProfileForSubscription(signatureProfile,subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search/{subscriptionId}")
    public ResponseEntity<?> getSubscriptionSignatureProfiles(@PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(signatureProfileService.findAllSignatureProfileForSubscription(subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateSignatureProfileToSubscription(@RequestBody SignatureProfile signatureProfile){
        try {
            return ResponseEntity.ok(signatureProfileService.updateSignatureProfileForSubscription(signatureProfile));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{signatureProfileId}")
    public ResponseEntity<?> deleteSignatureProfile(@PathVariable String signatureProfileId){
        try {
            signatureProfileService.deleteSignatureProfileForSubscription(signatureProfileId);
            return new ResponseEntity<>(signatureProfileId, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
