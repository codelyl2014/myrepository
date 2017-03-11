package com.chexiang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chexiang.dao.WorkersDao;
import com.chexiang.entity.Workers;
import com.chexiang.service.WorkersService;

@Service
public class WorkersServiceImpl implements WorkersService{
	@Resource
	private WorkersDao workersDao;
	
	@Override
    public List<Workers> getWorkerList(){
    	return this.workersDao.getWorkersList();
		
	}

	@Override
	public void addWorker(Workers workers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWorker(Workers workers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWorker(int id) {
		// TODO Auto-generated method stub
		
	}

}

	