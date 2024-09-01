package com.pairlearning.Tracker_api.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler 
{
	@ExceptionHandler(value = ApiReqException.class )
	public ResponseEntity<Object> handleApiRequestException(ApiReqException e)
	{
		HttpStatus badRequest = HttpStatus.PARTIAL_CONTENT;
		
		ApiException apiException =  new ApiException(
				e.getMessage(),
				HttpStatus.PARTIAL_CONTENT,
				ZonedDateTime.now(ZoneId.of("Z")));
		
		return new ResponseEntity<>(apiException, badRequest);
	}
}
