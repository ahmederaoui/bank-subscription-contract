package com.adriabt.accountsservice.controllers;

import com.adriabt.accountsservice.entities.CurrentAccount;
import com.adriabt.accountsservice.entities.SavingAccount;
import com.adriabt.accountsservice.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;
    @PostMapping("/savingAccount/create")
    public ResponseEntity<?> addSavingAccount(SavingAccount savingAccount){
        try {
            return ResponseEntity.ok(accountService.createSavingAccount(savingAccount));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/currentAccount/create")
    public ResponseEntity<?> addCurrentAccount(CurrentAccount currentAccount){
        try {
            return ResponseEntity.ok(accountService.createCurrentAccount(currentAccount));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
