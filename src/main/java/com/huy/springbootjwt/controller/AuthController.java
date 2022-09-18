package com.huy.springbootjwt.controller;

import java.io.Console;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huy.springbootjwt.dto.request.SignUpForm;
import com.huy.springbootjwt.dto.request.SignInForm;
import com.huy.springbootjwt.dto.response.JwtResponse;
import com.huy.springbootjwt.dto.response.ResponseMessage;
import com.huy.springbootjwt.model.Role;
import com.huy.springbootjwt.model.RoleName;
import com.huy.springbootjwt.model.User;
import com.huy.springbootjwt.security.jwt.JwtProvider;
import com.huy.springbootjwt.security.userpincal.UserPrinciple;
import com.huy.springbootjwt.service.impl.RoleServiceImpl;
import com.huy.springbootjwt.service.impl.UserServiceImpl;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
		@Autowired
		UserServiceImpl userServiceImpl;
		@Autowired
		RoleServiceImpl roleServiceImpl;	
		@Autowired
		PasswordEncoder passwordEncoder;
		@Autowired
		AuthenticationManager authenticationManager;
		@Autowired
		JwtProvider jwtProvider;
			
//		@PostMapping("/signup")
//		public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
//			 if(userServiceImpl.existsByUsername(signUpForm.getUsername())) {
//				 return new ResponseEntity<>(new ResponseMessage("The username is existed"), HttpStatus.OK);
//			 } 
//			 if(userServiceImpl.existsByEmail(signUpForm.getEmail())) {
//				 return new ResponseEntity<>(new ResponseMessage("The email is existed"), HttpStatus.OK);
//			 }
//			 
//			 User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
//			 Set<String> StrRoles = signUpForm.getRoles();
//			 Set<Role> roles = new HashSet<>();
//			 StrRoles.forEach(role -> {
//				 switch (role) {
//				case "admin": 
//						Role adminRole = roleServiceImpl.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
//						roles.add(adminRole);
//						break;	
//				case "pm":
//					Role pmRole = roleServiceImpl.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
//				roles.add(pmRole);
//				break;
//				default:
//					Role userRole = roleServiceImpl.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
//					roles.add(userRole);
//				 }
//			 });
//			 
//			 user.setRoles(roles);
//			 userServiceImpl.save(user);
//			 return new ResponseEntity<>(new ResponseMessage("Create success!"), HttpStatus.OK);
//		}	
		
		
		@PostMapping("/signin")
		public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm)
		{
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signInForm.getUsename(), signInForm.getPassword())
					);
			System.out.println(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtProvider.createToken(authentication);
			UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
			return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
		}
}
