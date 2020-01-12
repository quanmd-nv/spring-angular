package com.angular.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.angular.data.model.PostContentModel;

public interface PostContentRepository extends ReactiveMongoRepository<PostContentModel, String> {

}
