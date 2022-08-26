package com.rvs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rvs.ReservationRepository.ReservationRepo;
import com.rvs.exceptions.ReservationIDNotFound;
import com.rvs.model.Reservation;



public class ReservationService {
	
			@Autowired  
			ReservationRepo reservationRepo;  
			
		
			public List<Reservation> getAllReservations()   
			{  
			List<Reservation> reservationList = new ArrayList<Reservation>();  
			reservationRepo.findAll().forEach(reservation -> reservationList.add(reservation));
			return reservationList;  
			}  
			
			
			public Optional<Reservation> getReservationById(long id)   
			{  
				
				Reservation foundReservation = reservationRepo.findById(id).orElseThrow(ReservationIDNotFound::new);
				return Optional.ofNullable(foundReservation);
			}  

			//saving a specific record by using the method save() of CrudRepository  
			public Reservation saveOrUpdate(Reservation reservation)   
			{  
			return reservationRepo.save(reservation);  
			}
			
			
			public Reservation updateReservationByID(long id) {
				Reservation existingReservation = reservationRepo.findById(id)
																	.orElseThrow(ReservationIDNotFound::new);
				return reservationRepo.save(existingReservation);
			}
			
			//delete reservation
			public void delete(long id)   
			{  
			//can throw exception if not available
				reservationRepo.deleteById(id);
			} 
			
			
			
}
