package com.hp.accounts.repository;

import com.hp.accounts.models.Accounts;
import com.hp.accounts.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
}
