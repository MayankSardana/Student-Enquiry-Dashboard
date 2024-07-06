package com.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.binding.Login;
import com.dashboard.binding.SignUp;
import com.dashboard.binding.Unlock;
import com.dashboard.entity.User;
import com.dashboard.repository.UserRespository;
import com.dashboard.utility.MailUtility;
import com.dashboard.utility.PasswordUtil;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImp implements UserService
{
	@Autowired
	private PasswordUtil password;
	
	@Autowired
	private MailUtility mail;

	@Autowired
	private UserRespository userRepo;
	@Override
	public boolean grantLoginAccess(Login login) {
		User u = userRepo.findByUsername(login.getUsername());
		if(u==null || u.getAccountStatus().equals("LOCKED") ||!(u.getPassword().equals(login.getPassword()))) {
			return false;
		}
		return true;
	}

	@Override
	public String recoverPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handleSignUp(Unlock unlock , String email) {
		
		return false;
	}

	@Override
	public boolean sendMail(String password , String email) throws MessagingException {
		
		mail.sendEmail("Your Password","Password is ", password ,email);
		return true;
	}

	@Override
	public boolean unlockAccountPassword(Unlock unlock) {
		User u = userRepo.findByEmail(unlock.getEmail());
	    if(u.getPassword().equals(unlock.getTemporaryPassword()) && unlock.getNewPassword().equals(unlock.getConfirmPassword()))
	    {
	    	u.setPassword(unlock.getConfirmPassword());
		    u.setAccountStatus("UNLOCKED");
		    userRepo.save(u);
		    return true;
	    }
	    return false;
	}

	@Override
	public User saveUser(SignUp signup) throws MessagingException {
		User tofind = userRepo.findByEmail(signup.getEmail());
		if(tofind!=null) {
			return null;
		}
		User user = new User();
		user.setUsername(signup.getName());
		user.setPhoneNumber(signup.getPhone());
		user.setEmail(signup.getEmail());
		user.setAccountStatus("LOCKED");
		user.setPassword(password.generateSecurePassword());
		userRepo.save(user);
		StringBuilder str = new StringBuilder("");
		str.append("<h1>Click on the link below to unlock your account</h1>");
		str.append("<p>your password is </p>" + user.getPassword());
		mail.sendEmail("Unlock your Account", str.toString(),"http://localhost:8080/unlock?email=" +  signup.getEmail(), signup.getEmail());
		return user;
	}

}
