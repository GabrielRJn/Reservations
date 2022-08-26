package com.rvs.exceptions;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no reservation made by this email")
public class NoEmailFound extends EntityNotFoundException  {

}
