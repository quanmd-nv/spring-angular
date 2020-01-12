package com.angular.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collation =  "address_test")
public class ModelTest {

	private String country;
	private String city;
	private String street;
}
