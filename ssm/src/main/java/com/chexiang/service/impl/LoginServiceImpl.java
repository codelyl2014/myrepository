package com.chexiang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chexiang.dao.UserDao;
import com.chexiang.entity.User;
import com.chexiang.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	private UserDao userDao;
		
	@Override
	public User getUserByName(String name) {
		return this.userDao.selectUserByName(name);
		
	}
	@Override
	public String setUserName(String name){
		return this.userDao.getUserPassword(name);
	}

}
