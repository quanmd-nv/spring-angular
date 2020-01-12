package com.angular.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.angular.config.Constants;
import com.angular.config.security.AuthoritiesConstants;
import com.angular.data.model.UserInfomationModel;
import com.angular.data.repository.UserInfomationRepository;
import com.angular.exeption.UsernameAlreadyUsedException;
import com.angular.rest.dto.UserDTO;
import com.angular.util.DateTimeUtil;
import com.angular.util.RandomUtil;
import com.angular.util.SecurityUtils;

import reactor.core.publisher.Mono;

@Service
public class AccountService {

	private final Logger log = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private UserInfomationRepository infomationRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Mono<UserInfomationModel> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return infomationRepository.findByActivationKey(key)
            .flatMap(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                return infomationRepository.save(user);
            })
            .doOnNext(user -> log.debug("Activated user: {}", user));
    }
	
	
	
	public Mono<UserInfomationModel> registerUser(UserDTO user, String password) {
		return infomationRepository.findByUsername(user.getUsername()).flatMap(existingUser -> {
			if (existingUser.isActivated()) {
				throw new UsernameAlreadyUsedException();
			}
			return infomationRepository.delete(existingUser);
		}).thenReturn(new UserInfomationModel()).flatMap(newUser -> {
			newUser.setUsername(user.getUsername().toLowerCase());
			newUser.setFullName(user.getFullName());
			newUser.setPhone(user.getPhone());
			Set<String> roles = new HashSet<>();
			roles.add(AuthoritiesConstants.USER);
			newUser.setRoles(roles);
			if (StringUtils.isBlank(user.getLangKey())) {
				newUser.setLangKey(Constants.LANG_KEY);
			} else {
				newUser.setLangKey(user.getLangKey());
			}

			if (user.getEmail() != null) {
				newUser.setEmail(user.getEmail().toLowerCase());
			}
			// new user is not active
			newUser.setActivated(false);
			// new user gets registration key
			newUser.setActivationKey(RandomUtil.generateActivationKey());
			String passwordEncodeStr = passwordEncoder.encode(password);
			newUser.setPassword(passwordEncodeStr);
			newUser.setCreatedBy(user.getUsername().toLowerCase());
			newUser.setCreatedDate(DateTimeUtil.getInstant());
			return infomationRepository.save(newUser)
					.doOnNext(createdUser -> log.debug("Created Information for User: {}", createdUser.getUsername()));
		});
	}
	
	public Mono<UserInfomationModel> getCurrentUserLogin() {
		return SecurityUtils.getCurrentUser().flatMap(this.infomationRepository::findByUsername);
	}

	public Mono<UserInfomationModel> requestPasswordReset(String email) {
		return this.infomationRepository.findByEmailAndIsActivated(email).filter(activated -> activated.isActivated())
				.flatMap(user -> {
					user.setResetDate(DateTimeUtil.getInstant());
					user.setResetKey(RandomUtil.generateResetKey());
					return infomationRepository.save(user);
				});
	}
	
    public Mono<UserInfomationModel> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return infomationRepository.findByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(DateTimeUtil.getInstant().minusSeconds(86400)))
            .flatMap(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                return infomationRepository.save(user);
            });
    }
	
}
