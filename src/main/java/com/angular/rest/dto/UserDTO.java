package com.angular.rest.dto;

import java.time.Instant;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bson.types.Binary;

import com.angular.config.Constants;
import com.angular.data.model.UserInfomationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO representing a user, with his authorities.
 */
@Data
@NoArgsConstructor
public class UserDTO {

	private String id;

	@NotBlank
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String username;

	@Size(max = 50)
	private String fullName;

	@Email
	@Size(min = 5, max = 254)
	private String email;

	private boolean activated = false;

	@Size(min = 2, max = 10)
	private String langKey;

	private Set<String> roles;
	
	private String phone;
	
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String createdBy;

	private Instant createdDate;
	
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String lastModifiedBy;
	
	private Instant lastModifiedDate;

	private Binary avatar;

	public UserDTO(UserInfomationModel user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.activated = user.isActivated();
		this.langKey = user.getLangKey();
		this.roles = user.getRoles();
		this.phone = user.getPhone();
		this.createdBy = user.getCreatedBy();
		this.createdDate = user.getCreatedDate();
		this.lastModifiedBy = user.getLastModifiedBy();
		this.lastModifiedDate = user.getLastModifiedDate();
		this.avatar = user.getAvatar();
	}
}
