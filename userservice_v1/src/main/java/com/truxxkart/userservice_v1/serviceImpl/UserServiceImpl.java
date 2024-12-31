package com.truxxkart.userservice_v1.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bouncycastle.crypto.SavableDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.userservice_v1.entity.User;
import com.truxxkart.userservice_v1.repository.UserRepository;
import com.truxxkart.userservice_v1.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(User seller) {
		User createdUser = userRepo.save(seller);
		return createdUser;
	}

	@Override
	public List<User> getAllUser() {
		List<User> allUser = userRepo.findAll();
		return allUser;
	}

	@Override
	public User getUserById(Long id) {
		User userById = userRepo.findById(id).get();
		return userById;
	}

//	@Override
//	public User updateUser(User seller) {
//		User myUser = userRepo.findById(seller.getId()).get();
//		if (myUser != null) {
//			myUser = userRepo.save(seller);
//		}
//		return myUser;
//	}

//	@Override
//	public User findByEmail(String email) {
//		return userRepo.findByEmail(email).get();
//	}

	@Override
	public User findByfield(String field, String findBy) {
		if (field.equalsIgnoreCase("EMAIL")) {
			Optional<User> user = userRepo.findByEmail(findBy);
			if (user.isPresent()) {
				return user.get();
			}
		} else if (field.equalsIgnoreCase("PHONE")) {
			Optional<User> user = userRepo.findByPhone(findBy);
			if (user.isPresent()) {
				return user.get();
			}
		}
		return null;
	}

	@Override
	public List<User> findUserByVerificationOrActivation(String field, Boolean findBy) {
		if (field.equalsIgnoreCase("VERIFICATION")) {
			Optional<List<User>> userList = userRepo.findByIsVerified(findBy);
			if (userList.isPresent()) {
				return userList.get();
			}
		} else if (field.equalsIgnoreCase("ACTIVATION")) {
			Optional<List<User>> userList = userRepo.findByIsActive(findBy);
			if (userList.isPresent()) {
				return userList.get();
			}
		}
		return null;
	}

	@Override
	public List<User> findUserByCreationDate(LocalDate data) {
		return userRepo.findAll().stream().filter(user -> user.getCreatedAt().toLocalDate().isEqual(data))
				.collect(Collectors.toList());

	}

	@Override
	public User updateUserProfile(Long userId, String field, String toBeUpdated) {
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			User user = optUser.get();

			if (field.equalsIgnoreCase("NAME")) {
				user.setName(toBeUpdated);
			} else if (field.equalsIgnoreCase("EMAIL")) {
				user.setEmail(toBeUpdated);
			} else if (field.equalsIgnoreCase("PHONE")) {
				user.setPhone(toBeUpdated);
			} else if (field.equalsIgnoreCase("PASSWORDHASH")) {
				user.setPasswordHash(toBeUpdated);
			} 
			else if (field.equalsIgnoreCase("ROLE")) {
				user.setRole(toBeUpdated);
			} 
			else if (field.equalsIgnoreCase("USERTYPE")) {
				user.setUserType(toBeUpdated);
			} 
			return userRepo.save(user);

		}
		return null;
	}

	@Override
	public User updateUserStatus(Long userId, String field, Boolean toBeUpdated) {
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			User user = optUser.get();

			if (field.equalsIgnoreCase("VERIFICATION")) {
				user.setIsVerified(toBeUpdated);
			} else if (field.equalsIgnoreCase("ACTIVATION")) {
				user.setIsActive(toBeUpdated);
			}

			return userRepo.save(user);

		}
		return null;
	}


	@Override
	public List<User> findUserByRoleOrType(String field, String findBy) {
		if (field.equalsIgnoreCase("ROLE")) {
			return userRepo.findByRole(findBy).get();
		} else if (field.equalsIgnoreCase("USERTYPE")) {
			return userRepo.findByUserType(findBy).get();
		}

		return null;
	}

}