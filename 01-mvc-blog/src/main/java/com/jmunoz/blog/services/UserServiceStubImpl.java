package com.jmunoz.blog.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class UserServiceStubImpl implements UserService {

	@Override
	public boolean authenticate(String username, String password) {
		// PRovide a sample password check: username == password;
		return Objects.equals(username, password);
	}

}
