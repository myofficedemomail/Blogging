package com.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
	private Integer userid;
	@NotEmpty(message = "Name Can Not Be Empty")
	@Size(min = 4, max = 25, message = "Username should be between 4 to 25 characters !")
	private String name;
	@NotEmpty
	@Email(message = "Email address is not valid !")
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;
}
