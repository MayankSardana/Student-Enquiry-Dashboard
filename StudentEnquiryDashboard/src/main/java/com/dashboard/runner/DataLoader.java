package com.dashboard.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.dashboard.entity.Courses;
import com.dashboard.entity.EnquiryStatus;
import com.dashboard.repository.CourseRepository;
import com.dashboard.repository.EnquiryStatusRepository;

@Component
public class DataLoader implements ApplicationRunner
{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private EnquiryStatusRepository statusRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		courseRepo.deleteAll();
		statusRepo.deleteAll();
		
		Courses c1 = new Courses();
		c1.setCid(101);
		c1.setCourseName("Spring Boot and Microservices");
		
		Courses c2 = new Courses();
		c2.setCid(202);
		c2.setCourseName("Python Full Stack");
		
		Courses c3 = new Courses();
		c3.setCid(303);
		c3.setCourseName("AWS");
		
		Courses c4 = new Courses();
		c4.setCid(404);
		c4.setCourseName("Core Java + Java 8 Features");
		
		EnquiryStatus s1 = new EnquiryStatus();
		s1.setStatus_id(111);
		s1.setStatus("NEW");
		
		EnquiryStatus s2 = new EnquiryStatus();
		s2.setStatus_id(222);
		s2.setStatus("ENROLLED");
		
		EnquiryStatus s3 = new EnquiryStatus();
		s3.setStatus_id(333);
		s3.setStatus("LOST");
		
		courseRepo.saveAll(Arrays.asList(c1 , c2 , c3 , c4));
		statusRepo.saveAll(Arrays.asList(s1 , s2 , s3));
	}
        
}
