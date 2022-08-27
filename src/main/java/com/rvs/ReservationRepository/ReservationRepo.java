package com.rvs.ReservationRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rvs.model.Reservation;

public interface ReservationRepo extends JpaRepository <Reservation, Long> {

	@Query("SELECT reservation"
			+ " FROM reservations"
			+ " WHERE%reservation.customer_name%")
	public Reservation getByCustomerName(String customerName);
		
			
			
			
			
}