package com.blog.payload;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {
	private List<PostDto> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalElement;
	private Integer totalPage;
	private Boolean lastPage;
}
