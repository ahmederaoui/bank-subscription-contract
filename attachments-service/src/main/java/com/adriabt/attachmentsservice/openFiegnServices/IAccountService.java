package com.adriabt.attachmentsservice.openFiegnServices;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ACCOUNTS-SERVICE")
public interface IAccountService {

}
