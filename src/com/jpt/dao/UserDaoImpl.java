package com.jpt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpt.model.UserLogin;
import com.jpt.model.User;
import com.jpt.model.UserOrder;




@Transactional(readOnly=false)
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	
	@Autowired
	SessionFactory sessionFactory;

	public Connection getConnection() {
		Connection conn = null;
		String userName = "root";
		String password = "test";
		String url = "jdbc:mysql://localhost:3306/maydb";
		String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			System.out
					.println("Connection problem or Database does not exist........");
			e.printStackTrace();
		}

		return conn;
	}

	

	

	

	/*@Override
	public boolean createUser(User user) {
		boolean result = false;

		Connection connection = getConnection();
		String query = "insert into user(firstName, lastName, address, email, age) values(?,?,?,?,?)";
		PreparedStatement psmt = null;
		try {
			psmt = connection.prepareStatement(query);
			psmt.setString(1, user.getFirstName());
			psmt.setString(2, user.getLastName());
			psmt.setString(3, user.getAddress());
			psmt.setString(4, user.getEmail());
			psmt.setInt(5, user.getAge());
			psmt.execute();
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	
	
	/*
	 *  using Hibernate
	 *  downunder
	 */
	@Override
	public boolean createUser(User user){
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		session.save(user);
		
		//session.getTransaction().commit();
		return true;
	}
	
	
	

	/*@Override
	public User updateUser(User user) {

		Connection conn = getConnection();
		String query ="update user  set firstname = ?, lastName = ?, address = ?, email = ?, age = ?   WHERE email =  ?"  ;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getAddress());
			stmt.setString(4, user.getEmail());
			stmt.setInt(5, user.getAge());
			stmt.setString(6, user.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}*/
	
	
	@Override
	public User updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		return user;
	}
	
	
	@Override
	public User getUser(int id){
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}
	

	public String deleteU(int id){
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class,id);
		session.delete(user);
		return "success";
		
	}
	
	
/*
	@Override
	public List<User> getUsers() {
		Connection conn = null;
		conn = getConnection();
		CallableStatement cs=null;
		//Statement stmt = null;
		//String query = "select * from user";
		List<User> userList = new ArrayList<User>();
		String storedProcedureName = "{call get_all_users}";
		try {
			//stmt = conn.createStatement();
			
			cs = conn.prepareCall(storedProcedureName);
			ResultSet rs = cs.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setAge(rs.getInt("age"));
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = null;
		userList = (List<User>) session.createCriteria(User.class).list();
		return userList;
	}
	
	
	

	@Override
	public boolean deleteById(int id) {
		boolean result = false;

		Connection conn = null;
		conn = getConnection();
		PreparedStatement stmt = null;
		String query = "delete from user where id = "+id ;
		try {
			stmt = conn.prepareStatement(query);
			//stmt.setInt(1, id);
			int rows = stmt.executeUpdate();
			System.out.println(rows + "rows affected");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean deleteByEmail(String email) {
		boolean result = false;
		Connection conn = null;
		conn = getConnection();
		String query = "delete from user where email='"+email+"'";
		PreparedStatement ps =null;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error occured while performing delete operation: ");
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*@Override
	public User getUser(int id) {
		User user = null;
		Connection conn = null;
		conn = getConnection();
		Statement stmt = null;
		String query = "select * from user where id = "+id;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setAge(rs.getInt("age"));
			}
			//close the statement
			stmt.close();
			//close the connection
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}
	*/
	
	
	/*@Override
	public User getUser(String email) {
		Connection conn = null;
		conn = getConnection();
		Statement stmt = null;
		String query = "select * from user where email ='"+email+"'";
		try {
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setAge(rs.getInt("age"));
				return user;
			}
		} catch (SQLException e) {
			System.out.println("Error occured while executing query ....");
			e.printStackTrace();
		}
		
		return null;
	}

*/



	
	
	@Override
	public User getUser(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("email", email));
		User u = (User) c.uniqueResult();
		return u;

	}
	
	
	
	
	
	/*@Override
	public UserLogin loginUser(String userName, String password) {
		UserLogin login = null;
		Connection conn = null;
		conn = getConnection();
		Statement stmt = null;
		String query = "select * from login where email ='" + userName + "' and password ='"+password+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				login = new UserLogin();
				login.setUserName(rs.getString("userName"));
				login.setPassword(rs.getString("password"));				
			}
		} catch (SQLException e) {
			System.out.println("Error occured while retrieving the user .....");
			e.printStackTrace();
		}
		return login;
	}
	*/
	
	
	
	@Override
	public UserLogin loginUser(String userName, String password) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from UserLogin where userName=:userName AND password=:password ";
		Query q = session.createQuery(query);
			q.setParameter("userName", userName);
			q.setParameter("password", password);
			
			UserLogin ul = (UserLogin) q.uniqueResult();
	return ul;
	}
	
/*
	@Override
	public Set<User> getAllUserOrders(String firstName,String lastName) {
		Set<User> userSet = new HashSet<User>();
		User user = null;
		List<UserOrder> orderList = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		conn = getConnection();
		String storedProcedure = "{call get_all_customer_orders(?,?)}";
		
		try {
			cs = conn.prepareCall(storedProcedure);
			cs.setString(1, firstName);
			cs.setString(2, lastName);
			ResultSet rs = cs.executeQuery();
			
			//User oldUser = null;
			
			while(rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setAddress(rs.getString("address"));
			user.setEmail(rs.getString("email"));
			user.setAge(rs.getInt("age"));
			//userSet.add(user);
			
			UserOrder userOrder = new UserOrder();
			userOrder.setOrderName(rs.getString("orderName"));
			userOrder.setOrderDescription(rs.getString("orderDescription"));
			userOrder.setTotalPrice(rs.getDouble("totalPrice"));
			orderList.add(userOrder);
			user.setUserOrder(orderList);
			
			userSet.add(user);
			
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception occured ......");
			e.printStackTrace();
		}
		return userSet;
	}
	
	*/
	
	@Override
	public List<User> getAllUserOrders(String firstName,String lastName) {
	
		Session session = sessionFactory.getCurrentSession();
		//conjunction creates AND query
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("firstName", firstName));
		conjunction.add(Restrictions.eq("lastName", lastName));
		
		
		Criteria  c = session.createCriteria(User.class);
		/*c.add(Restrictions.eq("firstName", firstName));
		c.add(Restrictions.eq("lastName",lastName));*/
		c.add(conjunction);
		List<User> uList = c.list();
		
	return uList;
	
	}
	
	
	
	/*
	 * 
	 @Override
	public User getAllUserOrders(String firstName) {
		//Set<User> userSet = new HashSet<User>();
		User newUser = null;
		List<UserOrder> orderList = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		conn = getConnection();
		String storedProcedure = "{call get_all_customer_orders(?)}";
		
		try {
			cs = conn.prepareCall(storedProcedure);
			cs.setString(1, firstName);
			ResultSet rs = cs.executeQuery();
			
			//User oldUser = null;
			
			while(rs.next()){
			newUser = new User();
			newUser.setId(rs.getInt("id"));
			newUser.setFirstName(rs.getString("firstName"));
			newUser.setLastName(rs.getString("lastName"));
			newUser.setAddress(rs.getString("address"));
			newUser.setEmail(rs.getString("email"));
			newUser.setAge(rs.getInt("age"));
			//userSet.add(newUser);
			
			UserOrder userOrder = new UserOrder();
			userOrder.setOrderName(rs.getString("orderName"));
			userOrder.setOrderDescription(rs.getString("orderDescription"));
			userOrder.setTotalPrice(rs.getDouble("totalPrice"));
			orderList.add(userOrder);
			newUser.setUserOrder(orderList);
			
			//userSet.add(newUser);
			
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception occured ......");
			e.printStackTrace();
		}
		return newUser;
	}
	
	
	 
	 */
	
	
	
	//Creating a new User with the list of orders
	public void createUserOrder(User user, List<UserOrder> orderList){
		Session session = sessionFactory.getCurrentSession();
		user.setUserOrder(orderList);
		session.save(user);
		
	}
	
	
	
	
}
