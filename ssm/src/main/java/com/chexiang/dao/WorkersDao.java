package com.chexiang.dao;

import java.util.List;

import com.chexiang.entity.Workers;



public interface WorkersDao {
	public void addWorker(Workers workers);

	public void updateWorker(Workers workers);

	public void deleteWorker(int id);

	public List<Workers> getWorkersList();

}
