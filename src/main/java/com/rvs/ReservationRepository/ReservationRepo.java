package com.rvs.ReservationRepository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rvs.model.Reservation;

@Repository
public interface ReservationRepo extends CrudRepository <Reservation, Long> {

	@Query("SELECT r"
			+ " FROM Reservation r"
			+ " WHERE r.customerName like %:customerName%")
	public Iterable<Reservation> getByCustomerName(String customerName);
		
			
	@Query("SELECT r"
			+ " FROM Reservation r"
			+ " WHERE r.timeCreated like %:date%")
	public Optional<Reservation> getReservationByDateCreated(Date date);	
			
}