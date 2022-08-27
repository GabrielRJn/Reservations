package com.rvs.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,
reason = "No customer of this name made a reservation")
public class CustomerNotFoundException extends EntityNotFoundException {
	
	
}
