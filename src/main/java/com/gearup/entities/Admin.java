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
@Table(name="admins")
@AttributeOverride(name="id", column=@Column(name="admin_ids"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude="userDetails")
public class Admin extends BaseEntity{

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", nullable = false)
	private User userDetails;
	
}
