package com.angular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angular.data.model.EmailTemplateModel;
import com.angular.data.repository.EmailTemplateRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AdminService {

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	
	public Flux<EmailTemplateModel> findAllEmailTemplate() {
		return emailTemplateRepository.findAll();
	}
	
	public Mono<EmailTemplateModel> addEmailTemplate(EmailTemplateModel model) {
		return emailTemplateRepository.save(model);
	}
}
