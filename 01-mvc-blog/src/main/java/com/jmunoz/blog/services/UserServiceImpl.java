package com.jmunoz.blog.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmunoz.blog.dto.UserDto;
import com.jmunoz.blog.models.User;
import com.jmunoz.blog.repositories.UserRepository;
import com.jmunoz.blog.validation.UsernameExistsException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


	@Override
	public List<User> findAll() {
		return this.userRepo.findAll();
	}

	@Override
	public User findById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}

	@Override
	public User create(UserDto userDto) throws UsernameExistsException {
		
		if (usernameExists(userDto.getUsername())) {   
            throw new UsernameExistsException(
              "There is an account with that username: " + userDto.getUsername());
        }
		
		User user = new User();
		// assign user details to the user object
		user.setUsername(userDto.getUsername());
		user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
		user.setFullName(userDto.getFullName());
		
		return this.userRepo.save(user);
	}
	
    private boolean usernameExists(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }

	@Override
	public User edit(User user) {
		return this.userRepo.save(user);
	}

	@Override
	public void deleteById(Long id) {
		this.userRepo.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User
                (user.getUsername(), 
                user.getPasswordHash(), enabled, accountNonExpired, 
                credentialsNonExpired, accountNonLocked, 
                getAuthorities());
	}
	
	private final Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

}
