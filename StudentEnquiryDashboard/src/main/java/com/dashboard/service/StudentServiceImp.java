package com.dashboard.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.binding.Dashboard;
import com.dashboard.entity.StudentEnquiry;
import com.dashboard.entity.User;
import com.dashboard.repository.UserRespository;

@Service
public class StudentServiceImp implements StudentService
{

	@Autowired
	private UserRespository userRepo;
	
	@Override
	public Dashboard getLoginUserDtls(Integer userId) {
		 Optional<User> user = userRepo.findById(userId);
		 List<StudentEnquiry> students = null;
		 if(user.isPresent())
		 {
			  students = user.get().getList(); 
			  Integer total = students.size();
				Integer enrolled =  students.stream().filter(e->e.getStatus().equals("ENROLLED")).collect(Collectors.toList()).size();
				Integer lost = students.stream().filter(e->e.getStatus().equals("LOST")).collect(Collectors.toList()).size();
				
				Dashboard b = new Dashboard();
				b.setEnrolled(enrolled);
				b.setLost(lost);
				b.setTotalEnquiry(total);
				return b;
		 }
		return null;
	}

}
