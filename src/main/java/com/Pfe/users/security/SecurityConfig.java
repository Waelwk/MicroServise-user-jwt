package com.Pfe.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
	@Autowired
	UserDetailsService userDetailsService; @
	Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService) .passwordEncoder(bCryptPasswordEncoder);
	}
	protected void configure(HttpSecurity http) throws Exception { http.csrf().disable();
	http.sessionManagement(). sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	http.authorizeRequests().requestMatchers("/login").permitAll(); http.authorizeRequests().anyRequest().authenticated();
	
	} 
	}

