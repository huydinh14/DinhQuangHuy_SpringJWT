package com.huy.springbootjwt.service;

import java.util.Optional;

import com.huy.springbootjwt.model.Role;
import com.huy.springbootjwt.model.RoleName;

public interface IRoleService {
	Optional<Role> findByName(RoleName name);
}
