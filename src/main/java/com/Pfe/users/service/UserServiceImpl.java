package com.Pfe.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.Pfe.users.entities.Role;
import com.Pfe.users.entities.Users;
import com.Pfe.users.repos.RoleRepository;
import com.Pfe.users.repos.UserRepository;

import jakarta.transaction.Transactional;

@Transactional

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	UserRepository userRep;
	
	@Autowired
	RoleRepository roleRep;
	
	
	@Autowired
	 BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Users saveUser(Users user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRep.save(user);
	}

	@Override
	public Users addRoleToUser(String username, String rolename) {
		Users usr = userRep.findByUsername(username);
		Role r = roleRep.findByRole(rolename);
		
		usr.getRoles().add(r);
		return usr;
	}

	
	@Override
	public Role addRole(Role role) {
		return roleRep.save(role);
	}

	@Override
	public Users findUserByUsername(String username) {	
		return userRep.findByUsername(username);
	}

}