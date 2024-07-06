package com.dashboard.binding;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Student {
	
	Integer st_id;
   String studentName;
   String contact;
   String classMode;
   String course;
   String status;
}
