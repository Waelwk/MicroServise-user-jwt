package com.Pfe.users.security;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Pfe.users.entities.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
		}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Users user =null; try {
			user = new ObjectMapper().readValue(request.getInputStream(),
			Users.class);
			} catch (JsonParseException e) {
			e.printStackTrace();
			} catch (JsonMappingException e) {
			e.printStackTrace(); } catch (IOException e) {
			                e.printStackTrace();
			}
			return authenticationManager. authenticate(new
			UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	
	Authentication authResult) throws IOException, ServletException
	{
	org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User)
	authResult.getPrincipal();

	List<String> roles = new ArrayList<>(); 
	springUser.getAuthorities().forEach(au-> {
	      roles.add(au.getAuthority());
	});
	String jwt = JWT.create(). withSubject(springUser.getUsername()).
	withArrayClaim("roles", roles.toArray(new String[roles.size()])). withExpiresAt(new Date(System.currentTimeMillis()+10*24*60*60*1000)). sign(Algorithm.HMAC256("Wael-kamel@outlook.fr"));
	response.addHeader("Authorization", jwt);
}}
