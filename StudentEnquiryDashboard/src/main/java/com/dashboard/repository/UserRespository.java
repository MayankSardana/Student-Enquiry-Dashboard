package com.dashboard.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dashboard.entity.User;

public interface UserRespository extends JpaRepository<User, Integer> 
{
	public User findByEmail(String email);
	
	public User findByUsername(String username);

	
//	public List<StudentE>
}
