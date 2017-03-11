package com.chexiang.service;

import com.chexiang.entity.User;

public interface LoginService {
	
	public User getUserByName(String name);
	
	public String setUserName(String name);

}
