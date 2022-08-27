package com.rvs.model;



import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Reservation {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long reservationID; 
	
	@Column
	private String customerName;
	
	@Column(columnDefinition ="text")
	private String email;
	@Column
	private Date timeCreated;
	
	@Column(columnDefinition = "text")
	private String reservationDetails;

	
	

}
