package com.example.template.object;

import java.util.Date;

import lombok.Data;

@Data
public class Actor {

	private Integer id;
	private String firstName;
	private String lastName;
	private Date lastUpdated;
}
