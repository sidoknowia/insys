package com.insys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insys.dto.EnterpriseNodeDto;
import com.insys.exceptions.UnableToAddNodeException;
import com.insys.service.TreeEngineService;

@EnableAutoConfiguration
@Controller
@RequestMapping(path = "/tree")
public class TreeController {
	
	@Autowired
	TreeEngineService treeEngineService;
	
	@PostMapping(path = "/addNode")
	public @ResponseBody boolean addNewUser(@RequestBody EnterpriseNodeDto nodeDto) {
		try {
			return treeEngineService.addNode(nodeDto);
		} catch (UnableToAddNodeException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
