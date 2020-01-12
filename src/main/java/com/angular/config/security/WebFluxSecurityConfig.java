package com.angular.config.security;

import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;

import com.angular.config.security.jwt.JWTFilter;
import com.angular.config.security.jwt.TokenProvider;

@EnableWebFluxSecurity
@Configuration
public class WebFluxSecurityConfig {

	private final TokenProvider tokenProvider;

	private final ReactiveUserDetailsService reactiveUserDetailsService;

	public WebFluxSecurityConfig(final ReactiveUserDetailsService reactiveUserDetailsService, TokenProvider tokenProvider) {
		this.reactiveUserDetailsService = reactiveUserDetailsService;
		this.tokenProvider = tokenProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ReactiveAuthenticationManager reactiveAuthenticationManager() {
		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(
				reactiveUserDetailsService);
		authenticationManager.setPasswordEncoder(passwordEncoder());
		return authenticationManager;
	}

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity
			.securityMatcher(new NegatedServerWebExchangeMatcher(new OrServerWebExchangeMatcher(
					pathMatchers("/app/**", "/i18n/**", "/content/**", "/swagger-ui/index.html"),
					pathMatchers(HttpMethod.OPTIONS, "/**"))))
	        .csrf()
	        .disable()
	        .addFilterAt(new JWTFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
	        .authenticationManager(reactiveAuthenticationManager())
	        .exceptionHandling()
	    .and()
	        .headers()
	        .frameOptions()
	        .disable()
	    .and()
	    .cors().and()
	        .authorizeExchange()
	        .pathMatchers("/test/**").permitAll()
	        .pathMatchers("/blog/**").permitAll()
	        .pathMatchers("/api/register").permitAll()
	        .pathMatchers("/api/activate").permitAll()
	        .pathMatchers("/api/auth/authenticate").permitAll()
	        .pathMatchers("/api/account/reset-password/init").permitAll()
	        .pathMatchers("/api/account/reset-password/finish").permitAll()
	        .pathMatchers("/api/**").authenticated();
	    return httpSecurity.build();
	}
}
