package com.hemanshu.expensetracker.service;

import java.util.List;

import com.hemanshu.expensetracker.entity.Transaction;
import com.hemanshu.expensetracker.exception.EtBadRequestException;
import com.hemanshu.expensetracker.exception.EtResourceNotFoundException;

public interface TransactionService {
	
	public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);
	
	public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

	public Transaction saveTransaction(Transaction transaction) throws EtBadRequestException;
	
	public void removeTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
}
