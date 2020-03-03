package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long> {
	
}
