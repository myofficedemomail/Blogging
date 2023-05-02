package com.blog.service;

import java.util.List;

import com.blog.payload.UserDto;

public interface UserService {
	UserDto createUser(UserDto userdto);
	UserDto updateUser(UserDto userdto,Integer userid);
	List<UserDto> getAllUser();
	UserDto getUserById(Integer userid);
	void deleteUser(Integer userid);
	
}
