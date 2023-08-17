package com.adriabt.contractservice.controllers;
import com.adriabt.contractservice.entities.WebCeiling;
import com.adriabt.contractservice.services.ICeilingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webCeilings")
@RequiredArgsConstructor
public class WebCeilingController {
    private final ICeilingService<WebCeiling> ceilingService;
    @PostMapping("/create/{subscriptionId}")
    public ResponseEntity<?> addCeilingToSubscription(@RequestBody WebCeiling ceiling, @PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(ceilingService.createCeiling(ceiling,subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/search/{subscriptionId}")
    public ResponseEntity<?> getSubscriptionCeiling(@PathVariable String subscriptionId){
        try {
            return ResponseEntity.ok(ceilingService.FindCeiling(subscriptionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCeilingToSubscription(@RequestBody WebCeiling ceiling){
        try {
            return ResponseEntity.ok(ceilingService.updateCeiling(ceiling));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{subscriptionId}")
    public ResponseEntity<?> deleteCeiling(@PathVariable String ceilingId){
        try {
            ceilingService.deleteCeiling(ceilingId);
            return new ResponseEntity<>(ceilingId, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
