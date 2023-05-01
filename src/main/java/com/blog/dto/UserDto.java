package com.blog.dto;

import lombok.Data;

@Data
public class UserDto {
	private Integer userid;
	private String name;
	private String email;
	private String password;
	private String about;
}
