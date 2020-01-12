package com.angular.data.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.angular.rest.dto.PostStatus;

import lombok.Data;

@Data
@Document(collection = "post")
public class PostContentModel {

	private Instant creationDate;
	
	private Date publicationDate;
	
	@TextIndexed
	private String headline;
	
	@TextIndexed
	private String leadtext;
	
	@TextIndexed
	private String bodytext;
	
	private String quote;
	
	private String conclusion;
	
	private Binary image;
	
	private String topic;
	
	private String user;

	private PostStatus status;
	
	private List<CommentModel> comments;
}
