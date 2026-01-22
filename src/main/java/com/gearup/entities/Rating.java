package com.gearup.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="ratings")
@AttributeOverride(name="id", column=@Column(name="rating_id"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Rating extends BaseEntity{
	
	@Column(name="comments", length=100)
	private String comment;
	
	@Column(name="score")
	private double score;

}
