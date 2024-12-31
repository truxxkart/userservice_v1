package com.truxxkart.userservice_v1.service;

import java.time.LocalDate;
import java.util.List;

import com.truxxkart.userservice_v1.entity.User;

public interface UserService {
	public User createUser(User seller);

	public List<User> getAllUser();

	public User getUserById(Long id);

//	public User updateUser(User seller);

	public User findByfield(String field, String findBy);

	public List<User> findUserByVerificationOrActivation(String field, Boolean findBy);

	public List<User> findUserByCreationDate(LocalDate data);
	public List<User> findUserByRoleOrType(String field, String role);

	public User updateUserProfile(Long userId, String field, String toBeUpdated);

	public User updateUserStatus(Long userId, String field, Boolean toBeUpdated);

}
