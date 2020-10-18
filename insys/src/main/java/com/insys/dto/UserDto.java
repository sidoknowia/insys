package com.insys.dto;

import lombok.Getter;
import lombok.Setter;


public class UserDto {
	  @Getter @Setter
	  private String name;
	  
	  @Getter @Setter
	  private String email;
	  
	  @Getter @Setter
	  private String password;
	  
	  @Getter @Setter
	  int role;
}
