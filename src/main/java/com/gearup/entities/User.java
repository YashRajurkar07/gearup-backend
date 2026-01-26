package com.gearup.entities;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@AttributeOverride(name="id", column = @Column(name="user_id"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = "password")
public class User extends BaseEntity{
	
	@Column(name = "first_name", length=50)
	private String firstName;
	
	@Column(name = "last_name", length=50)
	private String lastName;
	
	@Column(length=70, unique=true, nullable = false)
	private String email;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Column(name="is_active")
	private boolean isActive;
	
	private String password;

	public User(String firstName, String lastName, String email, String mobileNumber, LocalDate dateOfBirth,
			Address address, Gender gender, boolean isActive, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.gender = gender;
		this.isActive = isActive;
		this.password = password;
	}
	
}
