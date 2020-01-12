package com.angular.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.angular.config.security.jwt.JWTFilter;
import com.angular.config.security.jwt.TokenProvider;
import com.angular.rest.dto.AuthRequestDto;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {

	private final Logger log = LoggerFactory.getLogger(AuthenticationResource.class);
	
	@Autowired
	private ReactiveAuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider tokenProvider;

	@PostMapping("/authenticate")
	public Mono<ResponseEntity<JWTToken>> authorize(@Valid @RequestBody AuthRequestDto loginVM) {
		return Mono.just(loginVM)
				.flatMap(login -> authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()))
						.onErrorResume(throwable -> onAuthenticationError(login, throwable))
						.flatMap(auth -> onAuthenticationSuccess(login, auth))
						.map(auth -> tokenProvider.createToken(auth, Boolean.TRUE.equals(login.getRememberMe()))))
				.map(jwt -> {
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
					return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
				}).doOnSuccess(onSuccess -> log.info("The account {} has been logged in successfully.",
						loginVM.getUsername()));
	}

	private Mono<? extends Authentication> onAuthenticationSuccess(AuthRequestDto login, Authentication auth) {
		return Mono.just(login).map(AuthRequestDto::getUsername).thenReturn(auth);
	}

	private Mono<? extends Authentication> onAuthenticationError(AuthRequestDto login, Throwable throwable) {
		return Mono.just(login).map(AuthRequestDto::getUsername).then(Mono.error(throwable));
	}

	/**
	 * Object to return as body in JWT Authentication.
	 */
	static class JWTToken {

		private String idToken;

		JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("id_token")
		String getIdToken() {
			return idToken;
		}

		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}
}
