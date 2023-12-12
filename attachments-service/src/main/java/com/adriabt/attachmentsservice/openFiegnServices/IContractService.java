package com.adriabt.attachmentsservice.openFiegnServices;

import com.adriabt.attachmentsservice.models.ContractAttachmentDTO;
import com.adriabt.attachmentsservice.models.Subscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "CONTRACTS-SERVICE")
public interface IContractService {
    @PostMapping("/api/subscriptions/attach")
    void attachContract(@RequestBody ContractAttachmentDTO contractAttachmentDTO, @RequestHeader("Authorization")String token);
    @PostMapping("/api/subscriptions/detach")
    void detachContract(ContractAttachmentDTO contractAttachmentDTO, @RequestHeader("Authorization")String token);
    @GetMapping("/api/subscriptions/search/{subscriptionId}")
    Subscription getSubscriptionsById(@PathVariable String subscriptionId, @RequestHeader("Authorization")String token);
}
