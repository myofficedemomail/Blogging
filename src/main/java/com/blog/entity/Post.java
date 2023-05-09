package com.blog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postid;
	private String title;
	private String content;
	private String image;
	private Date addedDate;
	@ManyToOne
	@JoinColumn(name = "user_fk")
	private User user;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "post")
	private List<Comment> comment;
	@ManyToOne
	@JoinColumn(name = "category_fk")
	private Category category;
}
