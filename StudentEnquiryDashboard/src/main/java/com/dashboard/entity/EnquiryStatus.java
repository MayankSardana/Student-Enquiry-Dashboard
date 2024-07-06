package com.dashboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EnquiryStatus {
      
     	@Id 
	 Integer status_id;
	
	 String status;
	
}
