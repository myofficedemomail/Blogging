package com.blog.service;

import java.util.List;

import com.blog.payload.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categorydto);

	CategoryDto updateCategory(CategoryDto categorydto, Integer categoryid);

	List<CategoryDto> getAllCategory();

	CategoryDto getCategoryById(Integer categoryid);

	void deleteCategory(Integer categoryid);

}
