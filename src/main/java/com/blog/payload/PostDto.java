package com.blog.payload;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class PostDto {
	private Integer postid;
	private String title;
	private String content;
	private String image;
	private Date addedDate;
	private UserDto user;
	private CategoryDto category;
	private List<CommentDto> comment;
}
