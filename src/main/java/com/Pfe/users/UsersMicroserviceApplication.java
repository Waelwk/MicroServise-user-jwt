package com.Pfe.users;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Pfe.users.entities.Role;
import com.Pfe.users.entities.Users;
import com.Pfe.users.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UsersMicroserviceApplication {
	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(UsersMicroserviceApplication.class, args);
	}
	@PostConstruct
	/*void init_users() { 
		//ajouter les rôles
	userService.addRole(new Role(null,"ADMIN")); 
	userService.addRole(new Role(null,"USER"));
	
	       //ajouter les users
	userService.saveUser(new Users(null,"admin","123",true,null));
	userService.saveUser(new Users(null,"nadhem","123",true,null));
	userService.saveUser(new Users(null,"yassine","123",true,null));
	//ajouter les rôles aux users
	userService.addRoleToUser("admin", "ADMIN"); userService.addRoleToUser("admin", "USER");
	userService.addRoleToUser("nadhem", "USER");
	userService.addRoleToUser("yassine", "USER");
}*/
	@Bean
	BCryptPasswordEncoder getBCE() {
	return new BCryptPasswordEncoder();
	
}}
