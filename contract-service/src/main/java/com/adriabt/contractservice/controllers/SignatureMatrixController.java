package com.adriabt.contractservice.controllers;

import com.adriabt.contractservice.entities.SignatureMatrix;
import com.adriabt.contractservice.services.ISignatureMatrixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signatureMatrices")
@RequiredArgsConstructor
public class SignatureMatrixController {
    private final ISignatureMatrixService signatureMatrixService;
    @PostMapping("/create/{subscriptionId}")
    public ResponseEntity<?> addSignatureMatrixToSubscription(@RequestBody SignatureMatrix signatureMatrix, @PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(signatureMatrixService.createSignatureMatrixForSubscription(signatureMatrix,subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search/{subscriptionId}")
    public ResponseEntity<?> getSubscriptionSignatureMatrices(@PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(signatureMatrixService.findAllSignatureMatrixForSubscription(subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateSignatureMatrixToSubscription(@RequestBody SignatureMatrix signatureMatrix){
        try {
            return ResponseEntity.ok(signatureMatrixService.updateSignatureMatrixForSubscription(signatureMatrix));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{subscriptionId}")
    public ResponseEntity<?> deleteSignatureMatrix(@PathVariable String signatureMatrixId){
        try {
            signatureMatrixService.deleteSignatureMatrixForSubscription(signatureMatrixId);
            return new ResponseEntity<>(signatureMatrixId, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
