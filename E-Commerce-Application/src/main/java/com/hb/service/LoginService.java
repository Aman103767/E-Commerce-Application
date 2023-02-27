package com.hb.service;

import com.hb.exceptions.LoginException;
import com.hb.models.LoginDTO;

public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
