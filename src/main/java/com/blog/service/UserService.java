package com.blog.service;

import java.util.List;

import com.blog.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto userdto) throws Exception;
	UserDto updateUser(UserDto userdto,Integer userid);
	List<UserDto> getAllUser();
	UserDto getUserById(Integer userid);
	void deleteUser(Integer userid);
	
}
