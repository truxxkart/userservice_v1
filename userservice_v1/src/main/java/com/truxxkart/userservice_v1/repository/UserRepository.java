package com.truxxkart.userservice_v1.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.userservice_v1.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByPhone(String phone);
	Optional<List<User>> findByRole(String findBy);
	Optional<List<User>> findByUserType(String findBy);
	Optional<List<User>> findByIsVerified(boolean findBy);
	Optional<List<User>> findByIsActive(boolean findBy);
}
