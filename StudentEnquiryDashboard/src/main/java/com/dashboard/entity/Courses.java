package com.dashboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Courses {

	@Id
	Integer cid;
	String courseName;
	
}
