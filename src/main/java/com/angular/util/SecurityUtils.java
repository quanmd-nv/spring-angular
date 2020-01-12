package com.angular.util;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public final class SecurityUtils {

	private SecurityUtils() {
		// do nothing
	}

	public static Mono<String> getCurrentUser() {
		return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication).map(auth -> {
			if (auth == null) {
				return null;
			}
			if (auth.getPrincipal() instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) auth.getPrincipal();
				return userDetails.getUsername();
			} else if (auth.getPrincipal() instanceof String) {
				return (String) auth.getPrincipal();
			}
			return null;
		});
	}
}
