package com.jpt.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpt.dao.UserDao;
import com.jpt.model.User;
import com.jpt.model.UserLogin;

@Service("userService")    //this tells that this is a spring component
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)   //for managing DB related activities
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao ;
	
	@Override
	public boolean createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getUsers();
	}

	@Override
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUserById(int id) {
		return userDao.deleteById(id);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUser(id);
	}

	@Override
	public boolean deleteUserByEmail(String email) {
		return userDao.deleteByEmail(email);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUser(email);
	}

	@Override
	public UserLogin loginUser(String userName, String password) {
		return userDao.loginUser(userName, password);
	}

	@Override
	public List<User> getAllOrder(String firstName, String lastName) {
		return userDao.getAllUserOrders(firstName,lastName);
	}

	@Override
	public String deleteU(int id) {
		
		return userDao.deleteU(id);
	}
	
	
	
}
