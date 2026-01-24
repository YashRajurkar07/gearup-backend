package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.User;

public interface UserRepository extends JpaRepository<User , Long> {

	boolean existsByEmail(String email);
}
