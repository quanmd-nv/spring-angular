package com.angular.rest.dto;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.util.CollectionUtils;

import com.angular.data.model.CommentModel;
import com.angular.data.model.PostContentModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostsDto {

	private Instant creationDate;

	private Date publicationDate;

	private String headline;

	private String leadtext;

	private String conclusion;

	private Binary image;

	private String topic;

	private String user;

	private PostStatus status;

	private long comments;

	public PostsDto(PostContentModel model) {
		this.conclusion = model.getConclusion();
		this.creationDate = model.getCreationDate();
		this.headline = model.getHeadline();
		this.image = model.getImage();
		this.leadtext = model.getLeadtext();
		this.publicationDate = model.getPublicationDate();
		this.status = model.getStatus();
		this.topic = model.getTopic();
		this.user = model.getUser();
		this.comments = count(model.getComments(), 0);
	}
	
	public long count(List<CommentModel> comments, long countNum) {
		if (CollectionUtils.isEmpty(comments)) {
			return countNum;
		}
		for (int i = 0; i < comments.size(); i ++) {
			countNum += (count(comments.get(i).getComments(), 0) + 1);
		}
		return countNum;
	}
}
