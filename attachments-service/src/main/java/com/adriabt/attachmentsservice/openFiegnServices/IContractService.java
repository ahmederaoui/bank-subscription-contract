package com.adriabt.attachmentsservice.openFiegnServices;

import com.adriabt.attachmentsservice.models.ContractAttachmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CONTRACTS-SERVICE")
public interface IContractService {
    @PostMapping("/api/subscriptions/attach")
    void attachContract(@RequestBody ContractAttachmentDTO contractAttachmentDTO);
    @PostMapping("/api/subscriptions/detach")
    void detachContract(ContractAttachmentDTO contractAttachmentDTO);
}
