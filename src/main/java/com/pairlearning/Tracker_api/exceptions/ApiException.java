package com.pairlearning.Tracker_api.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ApiException {
	
	private final String message;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;

	
}
