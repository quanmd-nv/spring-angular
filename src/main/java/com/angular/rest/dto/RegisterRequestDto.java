package com.angular.rest.dto;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterRequestDto extends UserDTO {

	public static final int PASSWORD_MIN_LENGTH = 6;

	public static final int PASSWORD_MAX_LENGTH = 100;

	@Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
	private String password;

}
