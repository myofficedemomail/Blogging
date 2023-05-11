package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.CommentDto;
import com.blog.payload.PostDto;
import com.blog.service.CommentService;

@RestController
@RequestMapping("api/comment/")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@PostMapping("{postid}")
	public ResponseEntity<PostDto> comment(@RequestBody CommentDto commentdto,@PathVariable Integer postid){
		PostDto postdto = commentService.comment(commentdto, postid);
		return new ResponseEntity<>(postdto, HttpStatus.CREATED);
	}
	@DeleteMapping("{commentid}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentid){
		commentService.deleteComment(commentid);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
}
