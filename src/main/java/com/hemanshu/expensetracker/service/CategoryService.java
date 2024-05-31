package com.hemanshu.expensetracker.service;

import java.util.List;

import com.hemanshu.expensetracker.entity.Category;
import com.hemanshu.expensetracker.exception.EtBadRequestException;
import com.hemanshu.expensetracker.exception.EtResourceNotFoundException;

public interface CategoryService {

	public List<Category> fetchCategoriesOfUser(Integer userId);
	
	public Category fetchCategoryById(Integer categoryId, Integer userId) throws EtResourceNotFoundException;
	
	public Category saveCategory(Category category) throws EtBadRequestException;
	
	public void removeCategoryById(Integer categoryId, Integer userId) throws EtResourceNotFoundException;
}
