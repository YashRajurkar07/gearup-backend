package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.OwnerDto;
import com.gearup.entities.Owner;

public interface OwnerService {

	List<Owner> getAllOwnerDetails();

	ApiResponse registerOwner(OwnerDto ownerDetails);

	ApiResponse updateOwner(Long oid, OwnerDto ownerDetails);

	ApiResponse deleteOwnerById(Long oid);

}
