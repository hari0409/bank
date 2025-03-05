package com.bank.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bank.accounts.entity.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    public Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Accounts a WHERE a.customerId = :customerId")
    void deleteByCustomerId(Long customerId);
}
