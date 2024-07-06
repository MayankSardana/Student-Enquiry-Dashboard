package com.dashboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dashboard.binding.Login;
import com.dashboard.binding.Recover;
import com.dashboard.binding.Unlock;
import com.dashboard.entity.User;
import com.dashboard.repository.UserRespository;
import com.dashboard.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    
	@Autowired
	private UserService service_user;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRespository user_repo;
	
	@GetMapping("/login")
	 public String login(Model model)
	 {
		 model.addAttribute("loginuser", new Login());
		 return "Login";
	 }
	
	@PostMapping("/loginsave")
	public String UserLogin(@ModelAttribute("loginuser") Login login, Model model  , BindingResult res) {
		model.addAttribute("loginMsg" , "");
		if(service_user.grantLoginAccess(login)==false) {
			model.addAttribute("loginMsg" , "Invalid Credintials");
			return "Login";
		}
		session.setAttribute("userId", user_repo.findByUsername(login.getUsername()).getUser_id());
		    return "redirect:/dashboard";
	}
	
	@GetMapping("/signup")
	public String SignUp(Model model) {
		model.addAttribute("signupuser" , new com.dashboard.binding.SignUp());
		return "SignUp";
	}
	
	@PostMapping("/signupsave")
	public String signUpSave(@ModelAttribute("signupuser") com.dashboard.binding.SignUp signup, Model model , BindingResult res) throws MessagingException {
		 User mt = service_user.saveUser(signup);
		 model.addAttribute("msg", "");
		 if(mt!=null) {
			 model.addAttribute("msg", "Please check your mail to unlock your account");
		 }else
		 {
		 model.addAttribute("msg", "Invalid details");
		 }
		 return "SignUp";
	}
	

	@PostMapping("/unlocksave")
	public String unlockSave(@ModelAttribute("unlockuser") Unlock unlock)
	{
		boolean c = service_user.unlockAccountPassword(unlock);
		if(c)
		{
			session.setAttribute("userId", user_repo.findByEmail(unlock.getEmail()).getUser_id());
			return "redirect:/dashboard";
		}
		return "Unlock";
	}
	
	@GetMapping("/unlock")
	public String unlock(@RequestParam String email , Model model)
	{
		Unlock un = new Unlock();
		un.setEmail(email);
		model.addAttribute("unlockuser" , un);
		model.addAttribute("email", email);
		return "Unlock";
	}
	
	@GetMapping("/recover")
	public String recover(Model model) {
		model.addAttribute("recover", new Recover());
		return "Recover";
	}
	
	@PostMapping("/recoversave")
	public String Recoversave(@ModelAttribute("recover") Recover recover,Model model ,BindingResult res) throws MessagingException {
		User user = user_repo.findByEmail(recover.getEmail());
		model.addAttribute("recoverMail", "");	
		if(user!=null)
		{
			service_user.sendMail(user.getPassword(), user.getEmail());
			model.addAttribute("recoverMail", "Mail Sent");	
		}
		else
		{
			model.addAttribute("recoverMail", "Mail Not Sent");
		}
		return "Recover";
	}
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	
}
