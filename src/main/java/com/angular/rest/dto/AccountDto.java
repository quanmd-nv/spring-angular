package com.angular.rest.dto;

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

@Data
@NoArgsConstructor
public class AccountDto {

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

	private Binary avatar;
	
	public AccountDto(UserInfomationModel model) {
		this.activated = model.isActivated();
		this.avatar = model.getAvatar();
		this.email = model.getEmail();
		this.fullName = model.getFullName();
		this.langKey = model.getLangKey();
		this.phone = model.getPhone();
		this.roles = model.getRoles();
		this.username = model.getUsername();
	}
}
