package com.angular.data.model;

import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.angular.config.Constants;

import lombok.Data;

@Data
@Document(collection = "email_template")
public class EmailTemplateModel {

	@Indexed(unique = true)
	private String key;
	
	private String description;
	
	@NotNull
	private String template;
	
	private Instant createDate;
	
	@NotNull
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String createdBy;

}
