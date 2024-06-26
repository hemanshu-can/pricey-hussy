package com.hemanshu.expensetracker.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hemanshu.expensetracker.entity.Category;
import com.hemanshu.expensetracker.entity.Transaction;
import com.hemanshu.expensetracker.entity.User;
import com.hemanshu.expensetracker.service.CategoryService;
import com.hemanshu.expensetracker.service.TransactionService;
import com.hemanshu.expensetracker.service.UserService;

@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("categories/{categoryId}/transactions")
	public List<Transaction> getAllTransactions(HttpServletRequest request,
			@PathVariable("categoryId") Integer categoryId) {
		int userId = (Integer) request.getAttribute("userId");
		return transactionService.fetchAllTransactions(userId, categoryId);
	}

	@GetMapping("categories/{categoryId}/transactions/{transactionId}")
	public Transaction getTransactionById(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId,
			@PathVariable("transactionId") Integer transactionId) {
		int userId = (Integer) request.getAttribute("userId");
		return transactionService.fetchTransactionById(userId, categoryId, transactionId);
	}

	@PostMapping("categories/{categoryId}/transactions")
	public Transaction addTransaction(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId,
			@RequestBody Transaction transaction) {
		int userId = (Integer) request.getAttribute("userId");
		User user = userService.getUserById(userId);
		Category category = categoryService.fetchCategoryById(categoryId, userId);
		transaction.setUser(user);
		transaction.setCategory(category);
		return transactionService.saveTransaction(transaction);
	}

	@PutMapping("categories/{categoryId}/transactions/{transactionId}")
	public Transaction updateTransaction(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId,
			@PathVariable("transactionId") Integer transactionId, @RequestBody Transaction transaction) {
		int userId = (Integer) request.getAttribute("userId");
		User user = userService.getUserById(userId);
		Category category = categoryService.fetchCategoryById(categoryId, userId);
		transaction.setId(transactionId);
		transaction.setUser(user);
		transaction.setCategory(category);
		return transactionService.saveTransaction(transaction);
	}
	
	@DeleteMapping("categories/{categoryId}/transactions/{transactionId}")
	public String deleteTransaction(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId,
			@PathVariable("transactionId") Integer transactionId) {
		int userId = (Integer) request.getAttribute("userId");
		transactionService.removeTransactionById(userId, categoryId, transactionId);
		return "Deleted";
	}
}
