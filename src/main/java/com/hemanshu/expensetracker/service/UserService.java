package com.hemanshu.expensetracker.service;

import com.hemanshu.expensetracker.entity.User;
import com.hemanshu.expensetracker.exception.EtAuthException;

public interface UserService {

	public User validateUser(String email, String password) throws EtAuthException;
	
	public User registerUser(User user) throws EtAuthException;
	
	public User getUserById(Integer id) throws EtAuthException;
	
}
