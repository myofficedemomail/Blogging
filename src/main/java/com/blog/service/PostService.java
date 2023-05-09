package com.blog.service;

import java.util.List;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postdto,Integer userid,Integer categoryid);
	PostDto updatePost(PostDto postdto,Integer postid);
	void deletePost(Integer postid);
	PostResponse getAllPost(Integer pagesize, Integer pageNumber);
	PostDto getPostById(Integer postid);
	PostResponse getPostByUser(Integer userid,Integer pagesize, Integer pageNumber);
	PostResponse getPostByCategory(Integer categoryid,Integer pagesize, Integer pageNumber);
	List<PostDto> searchPost(String keyword);
}
