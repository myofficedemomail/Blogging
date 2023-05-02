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
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.UserDto;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userservice;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userdto) {
		UserDto createUserDto = userservice.createUser(userdto);
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto, @PathVariable("userid") Integer userid) {
		UserDto updatedUserDto = userservice.updateUser(userdto, userid);
		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);
	}

	@DeleteMapping("/{userid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userid") Integer userid) {
		userservice.deleteUser(userid);
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userid") Integer userid){
		UserDto userDto=userservice.getUserById(userid);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> allUserDto=userservice.getAllUser();
		return new ResponseEntity<>(allUserDto, HttpStatus.OK);
	}
}
