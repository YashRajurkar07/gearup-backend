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
@Table(name="garage_owners")
@AttributeOverride(name="id", column = @Column(name="owner_ids"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude="userDetails")
public class Owner extends BaseEntity{
	
	@Column(name="alternate_phone")
	private String alternatePhone;
	
	@Column(name="is_verified")
	private boolean isVerified;
	
	@Column(name="registration_number")
	private String registrationNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", nullable = false)
	private User userDetails;

}
