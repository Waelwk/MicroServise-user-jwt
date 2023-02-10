package com.Pfe.users.service;

import com.Pfe.users.entities.Role;
import com.Pfe.users.entities.Users;

public interface UserService {
	Users saveUser(Users user);
	Users findUserByUsername (String username);
	Role addRole(Role role);
	Users addRoleToUser(String username, String rolename);

}
