package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourseNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.repo.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryrepo;

	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		Category categoryEntity = new Category();
		try {
			BeanUtils.copyProperties(categoryEntity, categorydto);
		
		categoryEntity=categoryrepo.save(categoryEntity);
		BeanUtils.copyProperties(categorydto, categoryEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorydto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryid) {
		Category category = categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Categoryid", categoryid.toString()));
		category.setDescription(categorydto.getDescription());
		category.setTitle(categorydto.getTitle());
		Category updatedCategory = categoryrepo.save(category);
		try {
			BeanUtils.copyProperties(categorydto, updatedCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorydto;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<CategoryDto> listCategorydto = new ArrayList<>();
		List<Category> listCategory = categoryrepo.findAll();
		listCategory.stream().forEach((category) -> {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setCategoryid(category.getCategoryid());
			categoryDto.setTitle(category.getTitle());
			categoryDto.setDescription(category.getDescription());
			listCategorydto.add(categoryDto);
		});
		return listCategorydto;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryid) {
		Category category = categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Categoryid", categoryid.toString()));
		CategoryDto categoryDto = new CategoryDto();
		try {
			BeanUtils.copyProperties(categoryDto, category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryid) {
		Category category = categoryrepo.findById(categoryid)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "Categoryid", categoryid.toString()));
		categoryrepo.delete(category);
	}

}
