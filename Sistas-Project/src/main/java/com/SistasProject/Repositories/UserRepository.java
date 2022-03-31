package com.SistasProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SistasProject.Entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	
	User findByEmail(String email);
}
