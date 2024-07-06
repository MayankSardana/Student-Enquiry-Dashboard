package com.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dashboard.entity.StudentEnquiry;
import com.dashboard.entity.User;

public interface StudentRepository extends JpaRepository<StudentEnquiry , Integer>{

	
      List<StudentEnquiry> findByUser(User user);	
}
