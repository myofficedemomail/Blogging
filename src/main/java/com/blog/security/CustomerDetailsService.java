package com.blog.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blog.entity.User;
import com.blog.exception.ResourseNotFoundException;
import com.blog.repo.UserRepo;

@Component
public class CustomerDetailsService implements UserDetailsService{
	@Autowired
	private UserRepo userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from db
		User user = userrepo.findByEmail(username).orElseThrow(() -> new ResourseNotFoundException("User", "Username", username));
		return user;
	}

}
