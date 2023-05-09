package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/post/")
public class PostController {
	@Autowired
	private PostService postservice;
	@PostMapping("user/{userid}/category/{categoryid}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable Integer userid,@PathVariable Integer categoryid){
		PostDto createPost = postservice.createPost(postdto, userid, categoryid);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	@PutMapping("{postid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable Integer postid) {
		postdto=postservice.updatePost(postdto, postid);
		return new ResponseEntity<PostDto>(postdto, HttpStatus.OK);
	}
	@DeleteMapping("{postid}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postid) {
		postservice.deletePost(postid);
		return new ResponseEntity<String>("Post Deleted Successfully", HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageSize", defaultValue = "2",required = false) Integer pageSize
			,@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber) {
		PostResponse allPost = postservice.getAllPost(pageSize,pageNumber);
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}
	@GetMapping("{postid}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postid){
		PostDto postById = postservice.getPostById(postid);
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
	}
	@GetMapping("user/{userid}")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userid,@RequestParam(value = "pageSize", defaultValue = "2",required = false) Integer pageSize
			,@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber){
		PostResponse postResponse = postservice.getPostByUser(userid,pageSize,pageNumber);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	@GetMapping("category/{categoryid}")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryid,@RequestParam(value = "pageSize", defaultValue = "2",required = false) Integer pageSize
			,@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber){
		PostResponse postResponse = postservice.getPostByCategory(categoryid,pageSize,pageNumber);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	@GetMapping("searchpost/{keyword}")
	public ResponseEntity<List<PostDto>> getPostBySearchKeyword(@PathVariable String keyword){
		List<PostDto> allPost = postservice.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}
}
