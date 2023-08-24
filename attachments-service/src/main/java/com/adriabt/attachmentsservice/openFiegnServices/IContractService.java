package com.adriabt.attachmentsservice.openFiegnServices;

import com.adriabt.attachmentsservice.models.ContractAttachmentDTO;
import com.adriabt.attachmentsservice.models.Subscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CONTRACTS-SERVICE")
public interface IContractService {
    @PostMapping("/api/subscriptions/attach")
    void attachContract(@RequestBody ContractAttachmentDTO contractAttachmentDTO);
    @PostMapping("/api/subscriptions/detach")
    void detachContract(ContractAttachmentDTO contractAttachmentDTO);
    @GetMapping("/api/subscriptions/search/{subscriptionId}")
    Subscription getSubscriptionsById(@PathVariable String subscriptionId);
}
