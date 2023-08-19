package com.adriabt.accountsservice.services;

import com.adriabt.accountsservice.entities.CurrentAccount;
import com.adriabt.accountsservice.entities.SavingAccount;

public interface IAccountService {
    SavingAccount createSavingAccount(SavingAccount savingAccount);
    CurrentAccount createCurrentAccount(CurrentAccount currentAccount);
}
