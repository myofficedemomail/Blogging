package com.blog.service;

import java.util.List;

import com.blog.payload.PostDto;

public interface PostService {
	PostDto createPost(PostDto postdto,Integer userid,Integer categoryid);
	PostDto updatePost(PostDto postdto,Integer postid);
	void deletePost(Integer postid);
	List<PostDto> getAllPost();
	PostDto getPostById(Integer postid);
	List<PostDto> getPostByUser(Integer userid);
	List<PostDto> getPostByCategory(Integer categoryid);
	List<PostDto> searchPost(String keyword);
}
