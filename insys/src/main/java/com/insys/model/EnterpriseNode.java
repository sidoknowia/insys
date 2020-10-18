package com.insys.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.insys.enums.NodeType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
public class EnterpriseNode {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String location;
	
	@Getter @Setter
	private Enum<NodeType> nodeType;
	
	@Getter @Setter
	private Integer parentId = 0;
	
	@Getter @Setter
	private Integer level = 0;
	
	@Getter @Setter
	private Integer createdByUserId;
	
	@Getter @Setter
	private Date CreatedOn;
	
	@Getter @Setter
	private Date lastUpdateTime;
	
	@Getter @Setter
	private boolean isActive = true;
}
