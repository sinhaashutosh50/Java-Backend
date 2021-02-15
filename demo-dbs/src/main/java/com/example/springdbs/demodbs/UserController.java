package com.example.springdbs.demodbs;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import Model.User;
//import Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> getUsers() throws SQLException{
		//return all the users from the DB
		
		return userService.getUsers();
		
	}
	
	@GetMapping("/get_user")
	public User getUserById(@RequestParam("id") int id) throws SQLException {
		//return a particular user with that id
		return userService.getUser(id);
	}
	
	
	@PostMapping("/register_user")
	public void createUser(@RequestBody User user) throws SQLException { //Jackson mapper is used to convert JSON data into user object 
		
		//Insert this user into the DB
		
		userService.insert(user);
		
		
	}
}
