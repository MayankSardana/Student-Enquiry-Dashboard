package com.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dashboard.entity.Courses;

public interface CourseRepository extends CrudRepository<Courses, Integer> 
{
	@Query("select courseName from Courses")
     public List<String> getCourses();
}
