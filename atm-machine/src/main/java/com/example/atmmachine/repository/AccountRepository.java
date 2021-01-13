package com.example.atmmachine.repository;

import com.example.atmmachine.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNo(Long accountNo);
}
