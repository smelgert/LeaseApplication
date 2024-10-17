package com.smelgert.carlease.repository;

import com.smelgert.carlease.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom queries can be defined if needed
}
