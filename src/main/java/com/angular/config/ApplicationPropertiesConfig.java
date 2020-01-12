package com.angular.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class ApplicationPropertiesConfig {

	private CorsConfiguration cors = new CorsConfiguration();

	private SecurityConfigProperties security = new SecurityConfigProperties();
	
	private MailProperties mail = new MailProperties();

	@Data
	public class SecurityConfigProperties {

		private AuthenticationPropertiesConfig authentication = new AuthenticationPropertiesConfig();

		@Data
		public class AuthenticationPropertiesConfig {

			private JwtPropertiesConfig jwt = new JwtPropertiesConfig();

			@Data
			public class JwtPropertiesConfig {
				private String secret = DefaultsConfig.Security.Authentication.Jwt.secret;
				private String base64Secret = DefaultsConfig.Security.Authentication.Jwt.base64Secret;
				private long tokenValidityInSeconds = DefaultsConfig.Security.Authentication.Jwt.tokenValidityInSeconds;
				private long tokenValidityInSecondsForRememberMe = DefaultsConfig.Security.Authentication.Jwt.tokenValidityInSecondsForRememberMe;
			}
		}
	}
	
	@Data
	public class MailProperties {
		private boolean enable = DefaultsConfig.Mail.enable;
		private String baseUrl = DefaultsConfig.Mail.baseUrl;
		private String from = DefaultsConfig.Mail.from;
	}
}
