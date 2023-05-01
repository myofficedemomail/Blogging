package com.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentid;
	private String comment;
	@ManyToOne
	@JoinColumn(name = "post_fk")
	private Post post;
	@ManyToOne
	@JoinColumn(name = "user_fk")
	private User user;
}
