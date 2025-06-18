package com.hp.accounts.repository;

import com.hp.accounts.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

      Optional<Customer> findByMobileNumber(String mobileNumber);
}
