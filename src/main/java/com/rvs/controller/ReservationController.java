package com.rvs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rvs.model.Reservation;
import com.rvs.service.ReservationService;

public class ReservationController {
	//auto-wire the ReservationService class  
			@Autowired  
			ReservationService ReservationService;  
			
			@GetMapping("/")
			public String greeting() { 
				return "This is the Reservations page"
						+ "\nController Pages\n /allReservations to view all reservations";
			}
			
			//creating a get mapping that retrieves all the reservations detail from the database   
			@GetMapping("/allReservations")  
			private List<Reservation> getAllReservations()   
			{  
			return ReservationService.getAllReservations();  
			}  
			
			//creating a get mapping that retrieves the detail of a specific reservation  
			@GetMapping("/reservation/{reservationID}")
			private Optional<Reservation> getReservationById(@PathVariable("reservationID") long id)
			{  
			return ReservationService.getReservationById(id);  
			}  
			
			//creating a delete mapping that deletes a specified reservation  
			@DeleteMapping("/deletereservation/{reservationid}")  
			private void deletereservation(@PathVariable("reservationid") int reservationid)   
			{  
			ReservationService.delete(reservationid);  
			}  
			
			//Updating Reservations 
			@PostMapping("/updateReservation/{reservationid}")  
			private ResponseEntity<Reservation> updateReservation(@RequestBody Reservation existingReservation)   
			{  
				return new ResponseEntity<>(this.ReservationService.saveOrUpdate(existingReservation), HttpStatus.CREATED);
			}  
			
			//Adding new reservations
			@PutMapping("/saveReservations")  
			private Reservation addReservation(@RequestBody Reservation newReservation)   
			{  
			ReservationService.saveOrUpdate(newReservation);  
			return newReservation;  
			}  
}
