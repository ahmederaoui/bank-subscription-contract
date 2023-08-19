package com.adriabt.accountsservice.repositories;

import com.adriabt.accountsservice.entities.CurrentAccount;
import com.adriabt.accountsservice.entities.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount,String> {
}
