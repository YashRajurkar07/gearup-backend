package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
