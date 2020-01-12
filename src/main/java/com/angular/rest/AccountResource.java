package com.angular.rest;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angular.exeption.AccountResourceException;
import com.angular.exeption.EmailNotFoundException;
import com.angular.exeption.InvalidPasswordException;
import com.angular.rest.dto.AccountDto;
import com.angular.rest.dto.KeyAndPasswordDto;
import com.angular.rest.dto.RegisterRequestDto;
import com.angular.service.AccountService;
import com.angular.service.MailService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AccountResource {

	@Autowired
	private AccountService accountService;

	@Autowired
	private MailService mailService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Void> register(@Valid @RequestBody RegisterRequestDto dto) {
		if (!checkPasswordLength(dto.getPassword())) {
			throw new InvalidPasswordException();
		}
		return accountService.registerUser(dto, dto.getPassword()).doOnSuccess(mailService::sendActivationEmail).then();
	}

	@GetMapping("/activate")
	public Mono<Void> activateAccount(@RequestParam(value = "key") String key) {
		return accountService.activateRegistration(key)
				.switchIfEmpty(Mono.error(new AccountResourceException("No user was found for this activation key")))
				.then();
	}

	@GetMapping("/account")
	public Mono<AccountDto> fetch() {
		return accountService.getCurrentUserLogin().map(AccountDto::new)
				.switchIfEmpty(Mono.error(new AccountResourceException("User could not be fould")));
	}

	@PostMapping("/account/reset-password/init")
	public Mono<Void> resetPassword(@RequestBody String email) {
		return this.accountService.requestPasswordReset(email).switchIfEmpty(Mono.error(new EmailNotFoundException()))
				.doOnSuccess(this.mailService::sendPasswordResetMail).then();
	}

	@PostMapping(path = "/account/reset-password/finish")
    public Mono<Void> finishPasswordReset(@RequestBody KeyAndPasswordDto keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        return accountService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey())
            .switchIfEmpty(Mono.error(new AccountResourceException("No user was found for this reset key")))
            .then();
    }
	
	private static boolean checkPasswordLength(String password) {
		return !StringUtils.isEmpty(password) && password.length() >= RegisterRequestDto.PASSWORD_MIN_LENGTH
				&& password.length() <= RegisterRequestDto.PASSWORD_MAX_LENGTH;
	}
}
