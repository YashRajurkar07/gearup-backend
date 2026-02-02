package com.gearup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gearup.dtos.AuthenticationResult;
import com.gearup.entities.Address;
import com.gearup.entities.Admin;
import com.gearup.entities.Customer;
import com.gearup.entities.Gender;
import com.gearup.entities.Owner;
import com.gearup.entities.User;
import com.gearup.entities.UserRole;
import com.gearup.repositories.AdminRepository;
import com.gearup.repositories.CustomerRepository;
import com.gearup.repositories.OwnerRepository;
import com.gearup.repositories.UserRepository;
import com.gearup.security.jwt.JwtUtils;
import com.gearup.security.requestDto.LoginRequest;
import com.gearup.security.requestDto.SignupRequest;
import com.gearup.security.services.UserDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;

	// 1. Inject the specific repositories
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private OwnerRepository ownerRepo;
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public AuthenticationResult login(LoginRequest request) {

		// 1. Authenticate
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		// 2. Set Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// 3. Generate Token
		String jwt = jwtUtils.generateJwtToken(authentication);

		// 4. Get User Details
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		// The Authority String
		String roleName = userDetails.getAuthorities().iterator().next().getAuthority();
		UserRole role = UserRole.valueOf(roleName);

		// 5. Return Result
		return new AuthenticationResult(jwt, userDetails.getId(), userDetails.getEmail(), role, "Login Successful");
	}

	@Override
	public String register(SignupRequest req) {

		if (userRepository.existsByEmail(req.getEmail())) {
			return "Error: Email is already in use!";
		}

		User user = new User();
		user.setFirstName(req.getFirstName());
		user.setLastName(req.getLastName());
		user.setEmail(req.getEmail());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setMobileNumber(req.getMobileNumber());
		user.setDateOfBirth(req.getDateOfBirth());
		user.setActive(true); // Default to active

		if (req.getGender() != null) {
			user.setGender(Gender.valueOf(req.getGender().toUpperCase()));
		}

		Address address = new Address();
		address.setCity(req.getCity());
		address.setState(req.getState());
		address.setCountry(req.getCountry());
		address.setArea(req.getArea());
		address.setZipCode(req.getZipCode());
		user.setAddress(address);

		UserRole role = req.getRole() != null ? req.getRole() : UserRole.ROLE_CUSTOMER;
		user.setRole(role);

		switch (role) {
		case ROLE_OWNER:
			Owner owner = new Owner();
			owner.setUserDetails(user);
			owner.setAlternatePhone(req.getAlternatePhone());
			owner.setRegistrationNumber(req.getRegistrationNumber());
			owner.setVerified(false);
			ownerRepo.save(owner);
			break;

		case ROLE_ADMIN:
			Admin admin = new Admin();
			admin.setUserDetails(user);
			adminRepo.save(admin);
			break;

		case ROLE_CUSTOMER:
		default:
			Customer customer = new Customer();
			customer.setUserDetails(user);
			customer.setLicenseNumber(req.getLicenseNumber());
			customerRepo.save(customer);
			break;
		}

		return "User registered successfully!";
	}

}