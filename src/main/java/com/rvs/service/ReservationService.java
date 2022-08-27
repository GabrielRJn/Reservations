package com.rvs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvs.ReservationRepository.ReservationRepo;
import com.rvs.exceptions.ReservationIDNotFound;
import com.rvs.model.Reservation;


@Service
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

			 
			public Reservation saveOrUpdate(Reservation reservation)   
			{  
			return reservationRepo.save(reservation);  
			}
			
			public Reservation findCustomerByName(String customerName) {
				return reservationRepo.getByCustomerName(customerName);
			}
			
			
			//delete reservation
			public void delete(long id)   
			{  
			//can throw exception if not available
				reservationRepo.deleteById(id);
			} 
			
			
			
}
