package com.example.springdbs.demodbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//import DbUtils.DbOperations;
//import Model.User;

//@Component //To have the methods run at application startup
@Service  //This annotation also have @Component and as it is a service class, this annotation is mostly used
public class UserService {
	
	public static Logger logger = LoggerFactory.getLogger(UserService.class);

//	public static Connection  connection;
	
	@Autowired
	DbOperations operation;
	
//	public UserService() throws SQLException {
//		logger.info("I am going to create table now");
//		operation.getConnection();
//		
//	}
	

	//Function that includes query to insert a user
	public void insert(User user) throws SQLException {
	
		String sql = "insert into user(name,country,age) VALUES(\"" + user.getName() + "\", \"" + user.getCountry() + "\", " + user.getAge() + ")";
		
		Statement statement = operation.getConnection().createStatement();
		
		int rows_modified = statement.executeUpdate(sql);
		
		logger.info("No of rows inserted is : {}", rows_modified );
	}
	
	
	//Function that includes query to return list of all the users
	public List<User> getUsers() throws SQLException{

		String sql = "select * from user";
		
		Statement statement = operation.getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sql); // Here we can use only executeQuery command. Also it returns the data in the form of tables, not a list of users.
		
		List<User>  userList = new ArrayList<>(); 
		
		while(resultSet.next()) { //Iterate through the result set and captures the attributes of user class
			
			int id = resultSet.getInt(1); 
			String name = resultSet.getString(2);
			String country = resultSet.getString(3);
			int age = resultSet.getInt(4);
			
			User user = new User(id, name ,country, age);
			
			userList.add(user);   //Add all the users in the list	
		}
		
		return userList;
	}
	
	//Function that includes query to return a particular user
	public User getUser(int id) throws SQLException {
		
		String sql = "select * from user where id =" + id;
		
		Statement statement = operation.getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sql); // Here we can use only executeQuery command. Also it returns the data in the form of tables, not a list of users.

		while(resultSet.next()) { //Iterate through the result set and captures the attributes of user class
			
			int record_id = resultSet.getInt(1); 
			String name = resultSet.getString(2);
			String country = resultSet.getString(3);
			int age = resultSet.getInt(4);
			
			User user = new User(record_id, name ,country, age);
			
			return user;
		}
		
		logger.error("No user found for id : {}",id);
		return null; //If there is no user found, this will return null
		
		
		
	}
	
	
}
