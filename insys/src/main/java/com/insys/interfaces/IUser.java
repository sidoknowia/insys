package com.insys.interfaces;

import com.insys.dto.UserDto;
import com.insys.exceptions.UserAlreadyExistException;
import com.insys.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUser {
	User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;
	User findUserByEmail(String email);
	UserDetails loadUserByEmail(String email);
	UserDetails login(String email, String password);
}
