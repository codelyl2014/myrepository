package com.chexiang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chexiang.dao.UserDao;
import com.chexiang.entity.User;
import com.chexiang.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	@Override
	public User getUserByName(String username){
		return this.userDao.selectUserByName(username);
	}

}
