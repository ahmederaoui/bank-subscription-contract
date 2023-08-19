package com.adriabt.accountsservice.services.impl;

import com.adriabt.accountsservice.entities.CurrentAccount;
import com.adriabt.accountsservice.entities.SavingAccount;
import com.adriabt.accountsservice.repositories.CurrentAccountRepository;
import com.adriabt.accountsservice.repositories.SavingAccountRepository;
import com.adriabt.accountsservice.services.IAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final SavingAccountRepository savingAccountRepository;
    private final CurrentAccountRepository currentAccountRepository;
    @Override
    public SavingAccount createSavingAccount(SavingAccount savingAccount) {
        Long now =new Date().getTime();
        Long ran  = new Random().nextLong(100,999);
        savingAccount.setAccountNumber(Long.toString(now)+Long.toString(ran));
        return savingAccountRepository.save(savingAccount);
    }

    @Override
    public CurrentAccount createCurrentAccount(CurrentAccount currentAccount) {
        Long now =new Date().getTime();
        Long ran  = new Random().nextLong(100,999);
        currentAccount.setAccountNumber(Long.toString(now)+Long.toString(ran));
        return currentAccountRepository.save(currentAccount);
    }
}
