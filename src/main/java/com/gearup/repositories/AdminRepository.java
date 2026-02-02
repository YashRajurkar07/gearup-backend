package com.gearup.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByUserDetails_Id(Long userId);
}
