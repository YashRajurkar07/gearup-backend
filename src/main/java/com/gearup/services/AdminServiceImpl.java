package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.entities.Admin;
import com.gearup.repositories.AdminRepository;
import com.gearup.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final UserRepository userRepo;
	private final AdminRepository adminRepo;
	private final ModelMapper mapper;

//	Get All Admin Details
	@Override
	public List<Admin> getAllAdmins() {
		
		return adminRepo.findAll();
	}

}
