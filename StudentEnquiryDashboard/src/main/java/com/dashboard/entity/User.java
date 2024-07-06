package com.dashboard.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class User 
{
	@Id
	@GeneratedValue
    Integer user_id;
	String username;
	String email;
	String phoneNumber;
	String password;
	String accountStatus;

	@OneToMany(mappedBy="user")
	List<StudentEnquiry> list;
}
