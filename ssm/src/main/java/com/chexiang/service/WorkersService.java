package com.chexiang.service;

import java.util.List;

import com.chexiang.entity.Workers;

public interface WorkersService {
	public void addWorker(Workers workers);

	public void updateWorker(Workers workers);

	public void deleteWorker(int id);

	public List<Workers> getWorkerList();
}
