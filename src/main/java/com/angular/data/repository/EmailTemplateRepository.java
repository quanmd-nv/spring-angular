package com.angular.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.angular.data.model.EmailTemplateModel;

@Repository
public interface EmailTemplateRepository extends ReactiveMongoRepository<EmailTemplateModel, String> {

}
