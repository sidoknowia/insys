package com.insys.interfaces;

import java.util.HashMap;
import java.util.HashSet;

import com.insys.dto.EnterpriseNodeDto;
import com.insys.exceptions.UnableToAddNodeException;
import com.insys.model.EnterpriseNode;

public interface ITreeEngine {
	boolean addNode(EnterpriseNodeDto currentNode) throws UnableToAddNodeException;
	void resetTree();
	void deleteNode(EnterpriseNodeDto nodeDto);
	HashMap<Integer, HashSet<EnterpriseNode>> getTree();
}
