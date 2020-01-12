package com.angular.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angular.data.model.EmailTemplateModel;
import com.angular.exeption.BadUserRequestExeption;
import com.angular.service.AdminService;
import com.angular.util.DateTimeUtil;
import com.angular.util.SecurityUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin/email")
public class EmailTemplateResource {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add-email-template")
	public Mono<Void> addEmailTemplate(@RequestBody EmailTemplateModel model) {
		model.setCreateDate(DateTimeUtil.getInstant());
		SecurityUtils.getCurrentUser().doOnNext(next -> model.setCreatedBy(next));
		return adminService.addEmailTemplate(model)
				.switchIfEmpty(Mono.error(new BadUserRequestExeption("Could not add email template"))).then();
	}
	
	@GetMapping("/get-all")
	public Flux<EmailTemplateModel> getAllEmailTemplate() {
		return adminService.findAllEmailTemplate();
	}
}
