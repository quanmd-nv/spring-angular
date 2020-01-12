package com.angular.config;

public interface DefaultsConfig {

	interface Security {

		interface Authentication {
			interface Jwt {
				String secret = null;
				String base64Secret = null;
				long tokenValidityInSeconds = 1800; // 30 minutes
				long tokenValidityInSecondsForRememberMe = 2592000; // 30 days
			}
		}
	}
	
	interface Mail {
		boolean enable = false;
		String baseUrl = "";
		String from = "";
	}
}
