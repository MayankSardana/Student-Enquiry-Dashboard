package com.dashboard.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class StudentEnquiry 
{ 
	@Id
	@GeneratedValue
     Integer enquiry_id;
	String studentName;
	String studentNumber;
	String classMode;
	String courseName;
	String status;
	LocalDate createdDate;
	LocalDate updatedDate;
	
	@ManyToOne
	User user;
}
