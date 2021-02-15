package com.example.springdbs.demodbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import UserService;

@Component
public class DbOperations {
	
	public static Connection  connection;
	public static Logger logger = LoggerFactory.getLogger(DbOperations.class);
	
	public DbOperations() throws SQLException {
		getConnection();
		createTable();
	}

	
	//default port of mysql -
	public Connection getConnection() throws SQLException {
			
		if(connection==null) {
			logger.info("Getting the connection from driver manager");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","root");//It is used to get connection to your Database. This connects to the users Database

		}
		return connection;
		}
	
	
	
	//Create table at application startup if it doesn't exists
	public void createTable() throws SQLException {
		
		//SQL query for inserting a new table "user" in the "users" database
		String sql = "create table if not exists user(id INT primary key auto_increment, "
				+ "name VARCHAR(30), country VARCHAR(30), age INT)";
		
		//Execute this query
		
		Statement statement = connection.createStatement();
		
		boolean result = statement.execute(sql); //Can return false even if the operation is successful. Mostly used for creating a table
		
		logger.info("result of create operation is {}",result);
//		statement.executeQuery(sql); //Execute and return resultSet. Mostly used with select queries when we want to retrive the data
//		statement.executeUpdate(sql);//Return no of rows being affected.Works with any type of queries but only returns no of rows being affected
		
	}

}
