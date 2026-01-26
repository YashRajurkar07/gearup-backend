package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.AdminDto;
import com.gearup.entities.Admin;

public interface AdminService {

	List<Admin> getAllAdminDetails();

	ApiResponse registerNewAdmin(AdminDto adminDetails);

	ApiResponse updateAdminDetails(Long adminId, AdminDto adminDetails);

	ApiResponse deleteAdminById(Long adminId);

	ApiResponse verifyGarageOwner(Long ownerId, boolean isVerified);
	
}
