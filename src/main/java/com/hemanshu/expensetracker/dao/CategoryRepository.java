package com.hemanshu.expensetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hemanshu.expensetracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public List<Category> findByUserId(Integer id);
	
	public Category findByIdAndUserId(Integer categoryId, Integer userId);
	
}
