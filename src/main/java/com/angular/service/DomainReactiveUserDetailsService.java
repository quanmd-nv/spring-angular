package com.angular.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.angular.data.model.UserInfomationModel;
import com.angular.data.repository.UserInfomationRepository;
import com.angular.exeption.UserNotActivatedException;
import com.angular.exeption.UserNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class DomainReactiveUserDetailsService implements ReactiveUserDetailsService {

	@Autowired
	private UserInfomationRepository userInfomationRepository;

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		String lowerCase = StringUtils.lowerCase(username);
		return userInfomationRepository.findByUsername(username)
				.switchIfEmpty(Mono.error(new UserNotFoundException("User " + lowerCase + "was not found in database")))
				.map(this::userDetails);
	}

	private UserDetails userDetails(UserInfomationModel model) {
		if (!model.isActivated()) {
			throw new UserNotActivatedException("User " + model.getUsername() + " was not activated");
		}
		List<GrantedAuthority> grantedAuthorities = model.getRoles().stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new User(model.getUsername(), model.getPassword(), grantedAuthorities);
	}

}
