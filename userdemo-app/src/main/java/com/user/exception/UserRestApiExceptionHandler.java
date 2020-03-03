package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

@ControllerAdvice
public class UserRestApiExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> exceptionToDoHandler(UserException ex) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		
		if (ex instanceof NestedServletException) { 

			ErrorResponse error = new ErrorResponse();
			error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			error.setMessage("- Sorry but an erorr has occur while processing");
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Error my be due to malformed request.");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

}
