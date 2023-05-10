package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.appconfig.AppConstant;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.service.FileService;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/post/")
public class PostController {
	@Autowired
	private PostService postservice;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	@PostMapping("user/{userid}/category/{categoryid}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable Integer userid,@PathVariable Integer categoryid){
		PostDto createPost = postservice.createPost(postdto, userid, categoryid);
		return new ResponseEntity<>(createPost,HttpStatus.CREATED);
	}
	@PutMapping("{postid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable Integer postid) {
		postdto=postservice.updatePost(postdto, postid);
		return new ResponseEntity<>(postdto, HttpStatus.OK);
	}
	@DeleteMapping("{postid}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postid) {
		postservice.deletePost(postid);
		return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageSize", defaultValue = AppConstant.pageSize,required = false) Integer pageSize
			,@RequestParam(value = "pageNumber", defaultValue = AppConstant.pageNumber,required = false) Integer pageNumber) {
		PostResponse allPost = postservice.getAllPost(pageSize,pageNumber);
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}
	@GetMapping("{postid}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postid){
		PostDto postById = postservice.getPostById(postid);
		return new ResponseEntity<>(postById,HttpStatus.OK);
	}
	@GetMapping("user/{userid}")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userid,@RequestParam(value = "pageSize", defaultValue = AppConstant.pageSize,required = false) Integer pageSize
			,@RequestParam(value = "pageNumber", defaultValue =AppConstant.pageNumber,required = false) Integer pageNumber){
		PostResponse postResponse = postservice.getPostByUser(userid,pageSize,pageNumber);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	@GetMapping("category/{categoryid}")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryid,@RequestParam(value = "pageSize", defaultValue = AppConstant.pageSize,required = false) Integer pageSize
			,@RequestParam(value = "pageNumber", defaultValue = AppConstant.pageNumber,required = false) Integer pageNumber){
		PostResponse postResponse = postservice.getPostByCategory(categoryid,pageSize,pageNumber);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	@GetMapping("searchpost/{keyword}")
	public ResponseEntity<List<PostDto>> getPostBySearchKeyword(@PathVariable String keyword){
		List<PostDto> allPost = postservice.searchPost(keyword);
		return new ResponseEntity<>(allPost,HttpStatus.OK);
	}
	@PostMapping("image/{postid}")
	public ResponseEntity<PostDto> uploadImage(@PathVariable Integer postid,@RequestParam("image") MultipartFile image) throws IOException{
		PostDto post = postservice.getPostById(postid);
		String uploadImage = fileService.uploadImage(path, image);
		post.setImage(uploadImage);
		PostDto updatePost = postservice.updatePost(post, postid);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
	@GetMapping(value = "image/{postid}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable Integer postid,HttpServletResponse response) throws IOException {
		PostDto post = postservice.getPostById(postid);
		InputStream resource = fileService.getResource(path, post.getImage());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
