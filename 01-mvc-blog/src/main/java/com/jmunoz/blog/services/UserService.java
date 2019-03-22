package com.jmunoz.blog.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jmunoz.blog.dto.UserDto;
import com.jmunoz.blog.models.User;
import com.jmunoz.blog.validation.UsernameExistsException;

public interface UserService extends UserDetailsService {
	
	List<User> findAll();
	
    User findById(Long id);
    
    User create(UserDto userDto) throws UsernameExistsException;
    
    User edit(User user);
    
    void deleteById(Long id);

}
