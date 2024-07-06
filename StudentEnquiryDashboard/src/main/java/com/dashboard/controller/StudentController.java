package com.dashboard.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashboard.binding.Student;
import com.dashboard.entity.StudentEnquiry;
import com.dashboard.entity.User;
import com.dashboard.repository.CourseRepository;
import com.dashboard.repository.EnquiryStatusRepository;
import com.dashboard.repository.StudentRepository;
import com.dashboard.repository.UserRespository;
import com.dashboard.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController 
{
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRespository repo;
	@Autowired
	private CourseRepository courses;
	
	@Autowired
	private StudentService serviceStudent;
	
	@Autowired
	private EnquiryStatusRepository Enrepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping("/add")
	public String AddUser(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("options", courses.getCourses());
		model.addAttribute("options2", Enrepo.getStatus());
		return "addUser";
	}
	
	@GetMapping("/view")
	public String ViewUser(Model model) {
		Integer id = (Integer)session.getAttribute("userId");
		List<StudentEnquiry> list = studentRepo.findByUser(repo.findById(id).get());
		model.addAttribute("list", list);
		return "ViewUser";
	}
	
	@PostMapping("/studentsave")
	public String SaveAddedUser(@ModelAttribute("student") Student student , Model model)
	{
		StudentEnquiry enq = new StudentEnquiry();
		enq.setClassMode(student.getClassMode());
		enq.setCourseName(student.getCourse());
		enq.setStatus(student.getStatus());
		enq.setStudentName(student.getStudentName());
		enq.setStudentNumber(student.getContact());
		enq.setCreatedDate(LocalDate.now());
		student.setSt_id(enq.getEnquiry_id());
		enq.setUpdatedDate(null);
		Integer gg = (Integer) session.getAttribute("userId");
		Optional<User> op = repo.findById(gg);
		
		if(op.isPresent()) {
			enq.setUser(op.get());
		}else {
			enq.setUser(null);
		}
		studentRepo.save(enq);
		model.addAttribute("msg", "User Saved"); 
		return "addUser";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Integer uid = (Integer)session.getAttribute("userId");
		model.addAttribute("dashboard", serviceStudent.getLoginUserDtls(uid));
		System.out.println("ee:" + serviceStudent.getLoginUserDtls(uid));
		return "dashboard";
	}
	
	@GetMapping("/edit/{id}")
	public String editStudent(@ModelAttribute("student") Student s , @PathVariable("id") Integer id , Model model) {
	Optional<StudentEnquiry> st = studentRepo.findById(id);
	if(st.isPresent())
	{
		studentRepo.delete(studentRepo.findById(id).get());
		s.setClassMode(st.get().getClassMode());
		s.setContact(st.get().getStudentNumber());
		s.setCourse(st.get().getCourseName());
		s.setStatus(st.get().getStatus());
		s.setStudentName(st.get().getStudentName());
		s.setSt_id(id);
		model.addAttribute("student", s);
		model.addAttribute("options", courses.getCourses());
		model.addAttribute("options2", Enrepo.getStatus());
		return "addUser";
	}
	
	Integer idInteger = (Integer)session.getAttribute("userId");
	List<StudentEnquiry> list = studentRepo.findByUser(repo.findById(idInteger).get());
	model.addAttribute("list", list);
	return "redirect:/view";
	
	}
	
	
}
