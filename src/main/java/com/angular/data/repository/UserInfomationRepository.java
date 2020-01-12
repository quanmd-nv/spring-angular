package com.angular.data.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.angular.data.model.UserInfomationModel;

import reactor.core.publisher.Mono;

@Repository
public interface UserInfomationRepository extends ReactiveMongoRepository<UserInfomationModel, String> {

	Mono<UserInfomationModel> findByUsername(String username);
	
	Mono<UserInfomationModel> findByActivationKey(String activationKey);
	
	@Query("{email:'?0'}, isActivated: true")
	Mono<UserInfomationModel> findByEmailAndIsActivated(String email);
	
	@Query("{resetKey:'?0'}, isActivated: true")
	Mono<UserInfomationModel> findByResetKey(String resetKey);
}
