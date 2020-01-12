package com.angular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angular.data.model.PostContentModel;
import com.angular.data.repository.PostContentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostsService {

	@Autowired
	private PostContentRepository repository;
	
	public Flux<PostContentModel> getAllPosts() {
		return repository.findAll();
	}
	
	public Mono<PostContentModel> savePost(PostContentModel postContent) {
		return repository.save(postContent);
	}
}
