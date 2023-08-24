package com.adriabt.attachmentsservice.openFiegnServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USERS-SERVICE")
public interface ISubscriberService {
    @PostMapping("/api/subscribers/subscriber/attach")
    void attachSubscriber(@RequestParam String attachmentId,@RequestParam String subscriberId);
    @PostMapping("/api/subscribers/subscriber/detach")
    void detachSubscriber(@RequestParam String attachmentId,@RequestParam String subscriberId);

}
