package com.huy.springbootjwt.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huy.springbootjwt.model.Role;
import com.huy.springbootjwt.model.RoleName;
import com.huy.springbootjwt.repository.IRoleRepository;
import com.huy.springbootjwt.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	IRoleRepository roleRepository;

	@Override
	public Optional<Role> findByName(RoleName name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}
	
}
