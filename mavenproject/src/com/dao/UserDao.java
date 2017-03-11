package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao {
	// public User getUser(User user);

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int UserId);

	public List<User> getUserList();

//	public int getSum();
}