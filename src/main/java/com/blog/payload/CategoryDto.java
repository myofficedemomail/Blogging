package com.blog.payload;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CategoryDto {
	private Integer categoryid;
	@NotEmpty(message = "Title can not be empty !")
	private String title;
	@NotEmpty(message = "Description can not be empty !")
	private String description;
}
