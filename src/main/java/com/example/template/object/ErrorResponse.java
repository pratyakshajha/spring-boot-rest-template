package com.example.template.object;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private Integer statusCode;
	private String message;

}
