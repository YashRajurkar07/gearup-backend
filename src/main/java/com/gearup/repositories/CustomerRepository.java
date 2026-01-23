package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
