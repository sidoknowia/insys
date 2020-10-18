package com.insys.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insys.dto.UserDto;
import com.insys.exceptions.UserAlreadyExistException;
import com.insys.interfaces.IUser;
import com.insys.model.Privilege;
import com.insys.model.Role;
import com.insys.model.User;
import com.insys.repository.UserRepository;

@Service
public class UserService implements IUser, UserDetailsService  {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
		
		if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
		
		User u = new User();
		u.setName(userDto.getName());
		u.setEmail(userDto.getEmail());
		u.setPassword(passwordEncoder.encode(userDto.getPassword()));
	
		userRepository.save(u);
		
		return u;
	}

	@Override
	public User findUserByEmail(String email) {
		User u = userRepository.findByEmail(email);
		return u;
	}
	
	@Override
	public UserDetails loadUserByEmail(String email) {
		try {
            final User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + email);
            }

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		return loadUserByEmail(email);
	}
	
	public UserDetails login(String email, String password) {
		try{
			UserDetails ud = loadUserByUsername(email);
			if(ud.getPassword().equals(passwordEncoder.encode(password))){
				return ud;
			}
			
		} catch (UsernameNotFoundException e){
			return null;
		} catch (Exception e){
			return null;
		}
		
		return null;
	}
	
	private boolean emailExists(String email){
		try{
			String e = userRepository.findByEmail(email).getEmail();
			if(email.equals(e)){
				return true;
			}
		} catch(NullPointerException ne){
			return false;
		}
		return false;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }
	
	private List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }
	
	
	private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }		
}
