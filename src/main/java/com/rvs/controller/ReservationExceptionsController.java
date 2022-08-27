package com.rvs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rvs.exceptions.CustomerNotFoundException;
import com.rvs.exceptions.NoEmailFound;

import com.rvs.exceptions.ReservationIDNotFound;

@ControllerAdvice
public class ReservationExceptionsController {

	 @ExceptionHandler(value = ReservationIDNotFound.class)
	   public ResponseEntity<Object> exception(ReservationIDNotFound exception) {
	      return new ResponseEntity<>("Incorrect reservation ID.", HttpStatus.NOT_FOUND);
	   }
	 
	 @ExceptionHandler(value = NoEmailFound.class)
	   public ResponseEntity<Object> exception(NoEmailFound exception) {
	      return new ResponseEntity<>("Incorrect Email", HttpStatus.NOT_FOUND);
	   }
	 
	 
	 @ExceptionHandler(value = CustomerNotFoundException.class)
	 public ResponseEntity<Object> exception(CustomerNotFoundException exception) {
		 return new ResponseEntity<>("This customer does not exist", HttpStatus.NOT_FOUND);
	 }
	 
}
