package com.example.template.controller.advice;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.template.object.ErrorResponse;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorResponse handleException(DuplicateKeyException ex) {
		ErrorResponse message = new ErrorResponse();
		message.setDescription("Object already exists!");
		message.setMessage(ex.getMessage());
		return message;
	}
}
