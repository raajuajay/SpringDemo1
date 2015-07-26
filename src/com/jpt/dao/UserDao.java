package com.jpt.dao;
import java.util.List;
import java.util.Set;

import com.jpt.model.UserLogin;
import com.jpt.model.User;


// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao {

	
	public boolean createUser(User user);
	
	
	public User updateUser(User user);
	
	
	public List<User> getUsers();
	
	
	public boolean deleteById(int id);
	
	
	public boolean deleteByEmail(String email);
	
	
	public User getUser(int id);
	
	
	public User getUser(String email);
	
	
	public UserLogin loginUser(String userName, String password);
	
	public List<User> getAllUserOrders(String firstName, String lastName);
	
	public String deleteU(int id);
}
