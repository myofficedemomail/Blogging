package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.blog.payload.CategoryDto;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categorydto) {
		CategoryDto createCategoryDto = categoryService.createCategory(categorydto);
		return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryid}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categorydto,
			@PathVariable("categoryid") Integer categoryid) {
		CategoryDto updatedCategoryDto = categoryService.updateCategory(categorydto, categoryid);
		return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryid}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryid") Integer categoryid) {
		categoryService.deleteCategory(categoryid);
		return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/{categoryid}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryid") Integer categoryid) {
		CategoryDto userDto = categoryService.getCategoryById(categoryid);
		return new ResponseEntity<CategoryDto>(userDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> allCategoryDto = categoryService.getAllCategory();
		return new ResponseEntity<>(allCategoryDto, HttpStatus.OK);
	}
}
