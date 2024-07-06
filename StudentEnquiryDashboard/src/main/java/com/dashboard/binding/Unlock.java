package com.dashboard.binding;

import lombok.Data;

@Data
public class Unlock {  
	 String email;
     String temporaryPassword;
     String newPassword;
     String confirmPassword;
}
