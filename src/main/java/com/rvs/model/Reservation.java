package com.rvs.model;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long reservationID; 
	
	@Column
	private String customer;
	@Column
	private Email email;
	@Column
	private LocalDateTime timeCreated;
	
	@Column(columnDefinition = "text")
	private String reservationDetails;

	
	

}
