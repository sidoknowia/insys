package com.insys.dto;

import java.util.Date;

import com.insys.enums.NodeType;

import lombok.Getter;
import lombok.Setter;

public class EnterpriseNodeDto {
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String location;
	
	@Getter @Setter
	private Enum<NodeType> nodeType;
	
	@Getter @Setter
	private Integer parentId;
	
	@Getter @Setter
	private Integer level;
	
	@Getter @Setter
	private Integer createdByUserId;
	
	@Getter @Setter
	private Date CreatedOn;
	
	@Getter @Setter
	private Date lastUpdateTime;
	
	@Getter @Setter
	private boolean isActive = true;
}
