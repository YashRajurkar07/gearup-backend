package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceAlreadyExistsException;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.AdminDto;
import com.gearup.entities.Admin;
import com.gearup.entities.Owner;
import com.gearup.entities.UserRole;
import com.gearup.repositories.AdminRepository;
import com.gearup.repositories.OwnerRepository;
import com.gearup.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final UserRepository userRepo;
	private final AdminRepository adminRepo;
	private final OwnerRepository ownerRepo;
	private final ModelMapper mapper;

//	Get All Admin Details
	@Override
	public List<Admin> getAllAdminDetails() {
		
		return adminRepo.findAll();
	}

//	Register New Admin
	@Override
	public ApiResponse registerNewAdmin(AdminDto adminDetails) {
		
		if (userRepo.existsByEmail(adminDetails.getUserDetails().getEmail())) {
            throw new ResourceAlreadyExistsException("Email Already Exists, Try Another Email Id");
        }

        Admin newAdmin = mapper.map(adminDetails, Admin.class);

        newAdmin.getUserDetails().setRole(UserRole.ROLE_ADMIN);
        newAdmin.getUserDetails().setActive(true);

        Admin persistentData = adminRepo.save(newAdmin);

        return new ApiResponse("Admin Registered Successfully with ID: " + persistentData.getId(), "Success");
	}

//	Update Admin Details
	@Override
	public ApiResponse updateAdminDetails(Long adminId, AdminDto adminDetails) {
		
		Admin existingAdmin = adminRepo.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin with Id " + adminId + " Does Not Exist"));

        mapper.map(adminDetails, existingAdmin);
        adminRepo.save(existingAdmin);

        return new ApiResponse("Admin Details Updated Successfully for ID: " + adminId, "Success");
	}

//	Soft Delete Admin Details
	@Override
	public ApiResponse deleteAdminById(Long adminId) {
		
		Admin existingAdmin = adminRepo.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin with Id " + adminId + " Does Not Exist"));

        existingAdmin.getUserDetails().setActive(false);
        
        adminRepo.save(existingAdmin);

        return new ApiResponse("Admin with ID: " + adminId + " Deleted Successfully", "Success");
	}

//	Mark Garage Owner as Verified or Not
	@Override
	public ApiResponse verifyGarageOwner(Long ownerId, boolean isVerified) {
		
		Owner existingOwner = ownerRepo.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner ID " + ownerId + " Not Found"));
		
		existingOwner.setVerified(isVerified);
		ownerRepo.save(existingOwner);
		
		String statusMsg = isVerified ? "Verified" : "Unverified";
		
		return new ApiResponse("Owner ID " + ownerId + " is Now " + statusMsg, "Success");
	}
}
