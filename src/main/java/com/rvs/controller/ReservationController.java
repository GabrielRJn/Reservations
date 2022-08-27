package com.rvs.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rvs.exceptions.NoReservationsFromDateException;
import com.rvs.model.Reservation;
import com.rvs.service.ReservationService;

@RestController
public class ReservationController {
	//auto-wire the ReservationService class  
			@Autowired  
			ReservationService ReservationService;  
			
			@GetMapping("/")
			public String greeting() { 
				return "This is the Reservations page"
						+ "\nController Pages\n go to /allReservations to view all reservations";
			}
			
			//creating a get mapping that retrieves all the reservations detail from the database   
			@GetMapping("/allReservations")  
			private List<Reservation> getAllReservations()   
			{  
			return ReservationService.getAllReservations();  
			}  
			
			//creating a get mapping that retrieves the detail of a specific reservation  
			@GetMapping("/reservation/ids{reservationID}")
			private Optional<Reservation> getReservationById(@PathVariable("reservationID") long id)
			{  
			return ReservationService.getReservationById(id);  
			}
			
			@GetMapping("/reservation/names{customerName}")
			private Optional<List<Reservation>> getReservationByCustomer(@PathVariable("customerName") String name)
			{  
			return ReservationService.findCustomerByName(name);  
			}  
			
			@GetMapping("/reservation/date{yyyy-mm-dd}")
			private Optional<List<Reservation>> getReservationByDateCreated(@PathVariable("yyyy-mm-dd") Date date ) 
			{  
		
			return ReservationService.findByDateCreated(date);  
			
			
			} 
			
			
			//creating a delete mapping that deletes a specified reservation  
			@DeleteMapping("/deleteReservation/{reservationID}")  
			private void deletereservation(@PathVariable("reservationid") int reservationID)   
			{  
			ReservationService.delete(reservationID);  
			}  
			
			
			//Updating Reservations 
			@PostMapping("/updateReservation")  
			private ResponseEntity<Reservation> updateReservation(@RequestBody Reservation existingReservation)   
			{  
				return new ResponseEntity<>(this.ReservationService.saveOrUpdate(existingReservation), HttpStatus.CREATED);
			}  
			
			//Adding new reservations
			@PutMapping("/addNewReservations")  
			private Reservation addReservation(@RequestBody Reservation newReservation)   
			{  
			ReservationService.saveOrUpdate(newReservation);  
			return newReservation;  
			}  
}
