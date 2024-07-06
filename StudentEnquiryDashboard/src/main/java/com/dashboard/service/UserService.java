package com.dashboard.service;

import com.dashboard.binding.Login;
import com.dashboard.binding.SignUp;
import com.dashboard.binding.Unlock;
import com.dashboard.entity.User;

import jakarta.mail.MessagingException;

public interface UserService 
{
     
     public boolean grantLoginAccess(Login login);
     
     public String recoverPassword(String email);
     
     public boolean handleSignUp(Unlock unlock , String email);
     
     public boolean sendMail(String password , String email) throws MessagingException;
     
     public boolean unlockAccountPassword(Unlock unlock);
     
     public User saveUser(SignUp signup) throws MessagingException ;
     
}
