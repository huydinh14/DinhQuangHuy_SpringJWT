package com.huy.springbootjwt.service;

import java.util.Optional;
import com.huy.springbootjwt.model.User;

public interface IUserService {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email); 
	User save(User user);	
}
