package com.jpt.service;

import java.util.List;
import java.util.Set;

import com.jpt.model.User;
import com.jpt.model.UserLogin;

public interface UserService {
public boolean createUser(User user);
	
	public List<User> getAllUser();
	
	public User updateUser(User user);
	
	public boolean deleteUserById(int id);
	
	public boolean deleteUserByEmail(String email);
	
	public User getUserById(int id);
	
	public User getUserByEmail(String email);
	
	public UserLogin loginUser (String userName, String password);
	
	public List<User> getAllOrder(String name, String lastName);

	public String deleteU(int parseInt);

}
