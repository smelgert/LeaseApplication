package com.smelgert.carlease.repository;

import com.smelgert.carlease.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // You can define additional custom queries if necessary
}