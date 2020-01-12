package com.angular.data.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.angular.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document(collection = "users")
public class UserInfomationModel {

	@Id
	private String id;

	@Indexed(unique = true)
	@NotNull
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String username;

	@Email
	@Size(min = 5, max = 254)
	@Indexed(unique = true)
	private String email;

	private String fullName;

	@JsonIgnore
	@NotNull
	@Size(min = 60, max = 60)
	private String password;

	private boolean isActivated = true;

	private Set<String> roles = new HashSet<>();

	private String phone;

	@Size(min = 2, max = 10)
	private String langKey;

	@Size(max = 20)
	@JsonIgnore
	private String activationKey;

	@Size(max = 20)
	@JsonIgnore
	private String resetKey;

	private Instant resetDate = null;

	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String createdBy;

	private Instant createdDate;
	
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String lastModifiedBy;
	
	private Instant lastModifiedDate;
	
	private Binary avatar;

}
