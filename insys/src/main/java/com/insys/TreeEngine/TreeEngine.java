package com.insys.TreeEngine;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.insys.dto.EnterpriseNodeDto;
import com.insys.exceptions.UnableToAddNodeException;
import com.insys.interfaces.ITreeEngine;
import com.insys.model.EnterpriseNode;
import com.insys.repository.EnterpriseNodeRepository;

@Component
//@Scope("Singleton")
public class TreeEngine implements ITreeEngine {
	
	@Autowired
	EnterpriseNodeRepository enterpriseNodeRepository;

	private static TreeEngine engine = new TreeEngine();
	private static HashMap<Integer, HashSet<EnterpriseNode>> nodeChildrenMap = new HashMap<Integer, HashSet<EnterpriseNode>>();
	
	@PostConstruct
	public TreeEngine getInstance() {
		if (engine.nodeChildrenMap.size() == 0) {
			initData();
		}
		return engine;
	}

	private void initData() {

		Collection<EnterpriseNode> nodes = (Collection<EnterpriseNode>) enterpriseNodeRepository.findAll();
		if (nodes != null){
			for(EnterpriseNode node: nodes){
				if(node != null && node.isActive()){
					engine.nodeChildrenMap.getOrDefault(node.getParentId(), new HashSet<EnterpriseNode>()).add(node);
					if(!engine.nodeChildrenMap.containsKey(node.getId())){
						engine.nodeChildrenMap.put(node.getId(), new HashSet<EnterpriseNode>());
					}
				}
			}
		}
		
	}

	public boolean addNode(EnterpriseNodeDto currentNode) throws UnableToAddNodeException {

		// save to DB
		EnterpriseNode en = new EnterpriseNode();
		en.setName(currentNode.getName());
		en.setLocation(currentNode.getLocation());
		en.setNodeType(currentNode.getNodeType());
		en.setParentId(currentNode.getParentId());
		en.setLevel(currentNode.getLevel());
		
		Date d = new Date();
		
		en.setCreatedOn(d);
		en.setLastUpdateTime(d);
		
		enterpriseNodeRepository.save(en);
		
		// add to cache object
		engine.nodeChildrenMap.getOrDefault(currentNode.getParentId(), new HashSet<EnterpriseNode>()).add(en);
		
		return true;
	}
	
	@Override
	public void deleteNode(EnterpriseNodeDto currentNode) {
		
	}
	
	@Override
	public void resetTree() {
		engine.nodeChildrenMap.clear();
		initData();
	}

	@Override
	public HashMap<Integer, HashSet<EnterpriseNode>> getTree() {
		return engine.nodeChildrenMap;
	}

}
