package com.dashboard.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailUtility 
{
	@Autowired
    private JavaMailSender sender;
	
	public boolean sendEmail(String sub , String body , String tt , String to) throws MessagingException {
		
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper help = new MimeMessageHelper(mm , true);
		
		help.setSubject(sub);
		help.setText(body + " " + tt, true);
		help.setTo(to);
		sender.send(mm);
		  return true;
	}

	

	
}
