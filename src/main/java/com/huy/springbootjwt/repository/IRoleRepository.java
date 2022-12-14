package com.huy.springbootjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huy.springbootjwt.model.Role;
import com.huy.springbootjwt.model.RoleName;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(RoleName name);
}
