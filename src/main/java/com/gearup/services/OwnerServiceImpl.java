package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.dtos.UserDto;
import com.gearup.entities.Owner;
import com.gearup.repositories.OwnerRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepo;
	private final UserDto userDto;
	private final ModelMapper mapper;
	
//	Get All Owners Details
	@Override
	public List<Owner> getAllOwnerDetails() {

		return ownerRepo.findAll();
		
	}

}
