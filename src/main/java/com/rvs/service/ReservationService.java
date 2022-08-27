package com.rvs.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvs.ReservationRepository.ReservationRepo;
import com.rvs.exceptions.CustomerNotFoundException;
import com.rvs.exceptions.NoReservationsFromDateException;
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
				
				Reservation foundReservation = reservationRepo.findById(id)
															.orElseThrow(ReservationIDNotFound::new);
				return Optional.ofNullable(foundReservation);
			}  

			 
			public Reservation saveOrUpdate(Reservation reservation)   
			{  
			return reservationRepo.save(reservation);  
			}
			
			public Optional<List<Reservation>> findCustomerByName(String customerName) {
				List<Reservation> reservationList = new ArrayList<Reservation>();  
				
				reservationRepo.getByCustomerName(customerName)
								.forEach(reservation -> reservationList.add(reservation));
				if(reservationList.isEmpty()) throw new CustomerNotFoundException();
				
				return Optional.ofNullable(reservationList);  
			
			}
			
			
			public Optional<List<Reservation>> findByDateCreated(Date date)  {
				
				
				List<Reservation> reservationList = new ArrayList<Reservation>();  
				
				reservationRepo.getReservationByDateCreated(date)
								.forEach(reservation -> reservationList.add(reservation));
				if(reservationList.isEmpty()) throw new NoReservationsFromDateException();	
				
				
				return Optional.ofNullable(reservationList);
			
			
			}
			
			//delete reservation
			public void delete(long id)   
			{  
			//add can throw exception if not available
				reservationRepo.deleteById(id);
			} 
			
			
			
}
