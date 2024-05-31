package com.hemanshu.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemanshu.expensetracker.dao.CategoryRepository;
import com.hemanshu.expensetracker.entity.Category;
import com.hemanshu.expensetracker.exception.EtBadRequestException;
import com.hemanshu.expensetracker.exception.EtResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> fetchCategoriesOfUser(Integer userId){
		return categoryRepository.findByUserId(userId);
	}

	@Override
	public Category fetchCategoryById(Integer categoryId, Integer userId) throws EtResourceNotFoundException {
		try {
			return categoryRepository.findByIdAndUserId(categoryId, userId);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Resource not found");
		}
	}

	@Override
	public Category saveCategory(Category category) throws EtBadRequestException {
		try {
			return categoryRepository.save(category);
		} catch (Exception e) {
			throw new EtBadRequestException("Invalid request");
		}
	}

	@Override
	public void removeCategoryById(Integer categoryId, Integer userId)
			throws EtResourceNotFoundException {
		try {
			Category category = categoryRepository.findByIdAndUserId(categoryId, userId);
			categoryRepository.delete(category);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Resource not found");
		}

	}

}
