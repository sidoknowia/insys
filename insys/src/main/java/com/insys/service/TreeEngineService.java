package com.insys.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insys.TreeEngine.TreeEngine;
import com.insys.dto.EnterpriseNodeDto;
import com.insys.exceptions.UnableToAddNodeException;


@Service
public class TreeEngineService {
	
	@Autowired
	TreeEngine engine;
	

	public boolean addNode(EnterpriseNodeDto currentNode) throws UnableToAddNodeException {
		engine.getInstance().addNode(currentNode);
		return true;
	}

	

}
