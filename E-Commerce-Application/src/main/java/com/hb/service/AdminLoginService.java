package com.hb.service;

import com.hb.exceptions.LoginException;
import com.hb.models.LoginDTO;

public interface AdminLoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
