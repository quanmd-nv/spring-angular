package com.angular.config.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.angular.config.ApplicationPropertiesConfig;
import com.angular.config.ApplicationPropertiesConfig.SecurityConfigProperties.AuthenticationPropertiesConfig.JwtPropertiesConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	private static final String AUTHORITIES_KEY = "auth";

	private Key key;

	private long tokenValidityInMilliseconds;

	private long tokenValidityInMillisecondsForRememberMe;

	private ApplicationPropertiesConfig config;

	public TokenProvider(ApplicationPropertiesConfig config) {
		this.config = config;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] keyBytes;
		JwtPropertiesConfig jwtConfig = config.getSecurity().getAuthentication().getJwt();
		String secret = jwtConfig.getSecret();
		if (!StringUtils.isEmpty(secret)) {
			log.warn("Warning: the JWT key used is not Base64-encoded. "
					+ "We recommend using the `config.security.authentication.jwt.base64-secret` key for optimum security.");
			keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		} else {
			log.debug("Using a Base64-encoded JWT secret key");
			keyBytes = Decoders.BASE64.decode(jwtConfig.getBase64Secret());
		}
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.tokenValidityInMilliseconds = 1000
				* jwtConfig.getTokenValidityInSeconds();
		this.tokenValidityInMillisecondsForRememberMe = 1000
				* jwtConfig.getTokenValidityInSecondsForRememberMe();
	}

	public String createToken(Authentication authentication, boolean rememberMe) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date validity;
		if (rememberMe) {
			validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
		} else {
			validity = new Date(now + this.tokenValidityInMilliseconds);
		}

		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			log.info("Invalid JWT token.");
			log.trace("Invalid JWT token trace.", e);
		}
		return false;
	}
}
