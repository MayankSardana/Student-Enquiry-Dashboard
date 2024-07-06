package com.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dashboard.binding.Login;

@Controller
public class IndexController 
{
	@GetMapping("/")
     public String indexPage() {
    	  return "index";
     }
	
	
	
	@GetMapping("/adduser")
	public String AddStudent() {
		return "addUser";
	}
	
	@GetMapping("/viewuser")
	public String ViewStudent() {
		return "ViewUser";
	}
	
	
	
}
