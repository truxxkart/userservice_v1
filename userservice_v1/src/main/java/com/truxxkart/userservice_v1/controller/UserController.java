package com.truxxkart.userservice_v1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truxxkart.userservice_v1.entity.User;
import com.truxxkart.userservice_v1.serviceImpl.UserServiceImpl;



@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	

	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser =userService.createUser(user);
		return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUsers =userService.getAllUser();
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUsersById(@PathVariable Long id){
		User userById =userService.getUserById(id);
		return new ResponseEntity<User>(userById,HttpStatus.OK);
	}
	@GetMapping("/field")
	public ResponseEntity<User> getUserByField(@RequestParam String field,@RequestParam String findBy){
		User user =userService.findByfield(field, findBy);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<User>> getUserByVerificationOrActivation(@RequestParam String field,@RequestParam Boolean findBy){
		List<User> userList =userService.findUserByVerificationOrActivation(field, findBy);
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/role-or-type")
	public ResponseEntity<List<User>> findUserByRoleOrType(@RequestParam String field,@RequestParam String findBy){
		List<User> userList =userService.findUserByRoleOrType(field, findBy);
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/creation-date")
	public ResponseEntity<List<User>> findUserByCreationDate(@RequestParam LocalDate date){
		List<User> userList =userService.findUserByCreationDate(date);
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	@PutMapping("/update/id/{userId}")
	public ResponseEntity<User> updateUserProfile( @PathVariable Long userId ,@RequestParam String field,@RequestParam String toBeUpdated){
		User user =userService.updateUserProfile(userId, field, toBeUpdated);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@PutMapping("/update/status/id/{userId}")
	public ResponseEntity<User> updateUserStatus( @PathVariable Long userId ,@RequestParam String field,@RequestParam Boolean toBeUpdated){
		User user =userService.updateUserStatus(userId, field, toBeUpdated);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
