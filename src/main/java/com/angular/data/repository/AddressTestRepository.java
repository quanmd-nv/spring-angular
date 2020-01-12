package com.angular.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.angular.data.model.ModelTest;

import reactor.core.publisher.Flux;

@Repository
public interface AddressTestRepository extends ReactiveMongoRepository<ModelTest, String>{

	Flux<ModelTest> findByCountry(String country);
}
