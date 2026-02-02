package com.gearup.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

	Optional<Owner> findByUserDetails_Id(Long userId);
}
