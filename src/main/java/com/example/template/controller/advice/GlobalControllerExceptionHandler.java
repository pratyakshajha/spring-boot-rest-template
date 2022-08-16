package com.example.template.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.template.object.ErrorResponse;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleException(Exception ex) {
		ErrorResponse message = new ErrorResponse();
		message.setStatusCode(500);
		message.setMessage(ex.getMessage());

		return message;
	}
}
