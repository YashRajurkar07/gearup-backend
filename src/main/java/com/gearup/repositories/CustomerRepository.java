package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Customer;
import com.gearup.entities.UserRole;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByUserDetailsRole(UserRole role);
}
