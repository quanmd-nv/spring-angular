package com.angular.data.model;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class CommentModel {

	private String user;
	
	private Instant commentDate;
	
	private String message;
	
	private int like;
	
	private int disLike;
	
	private List<CommentModel> comments;
}
