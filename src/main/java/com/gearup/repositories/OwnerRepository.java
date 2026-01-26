package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
