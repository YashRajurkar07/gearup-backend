package com.gearup.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customers")
@AttributeOverride(name = "id", column = @Column(name="customer_ids"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude="userDetails")
public class Customer extends BaseEntity {

	@Column(name="license_number")
	private String licenseNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "user_id", nullable = false)
	private User userDetails;

	public Customer(String licenseNumber, User userDetails) {
		super();
		this.licenseNumber = licenseNumber;
		this.userDetails = userDetails;
	}
	
}
