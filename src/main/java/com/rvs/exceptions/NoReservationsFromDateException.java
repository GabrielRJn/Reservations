package com.rvs.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There was no reservation made at this date")
public class NoReservationsFromDateException extends EntityNotFoundException {

}
