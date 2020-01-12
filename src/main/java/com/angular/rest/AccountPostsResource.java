package com.angular.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.angular.data.model.PostContentModel;
import com.angular.exeption.PostException;
import com.angular.rest.dto.PostsDto;
import com.angular.service.PostsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("/api/account/posts")
public class AccountPostsResource {

	@Autowired
	private PostsService postsService;
	
	@GetMapping("/get-all")
	public Flux<PostsDto> getAllPosts() {
		return postsService.getAllPosts().map(PostsDto::new);
	}
	
	@PostMapping("/add-post")
	public Mono<Void> addPost(@RequestBody PostContentModel post) {
		return postsService.savePost(post).switchIfEmpty(Mono.error(new PostException())).then();
	}
}
