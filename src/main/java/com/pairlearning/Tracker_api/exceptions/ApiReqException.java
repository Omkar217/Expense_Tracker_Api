package com.pairlearning.Tracker_api.exceptions;

public class ApiReqException extends RuntimeException {
	
	public ApiReqException(String msg)
	{
		super(msg);
	}

	public ApiReqException(String msg, Throwable cause) 
	{
		super(msg, cause);
	}
}
