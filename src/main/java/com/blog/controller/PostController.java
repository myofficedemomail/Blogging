package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.PostDto;
import com.blog.service.PostService;

import lombok.Getter;

@RestController
@RequestMapping("api/post")
public class PostController {
	@Autowired
	private PostService postservice;
	@PostMapping("/user/{userid}/category/{categoryid}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable Integer userid,@PathVariable Integer categoryid){
		PostDto createPost = postservice.createPost(postdto, userid, categoryid);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
}
