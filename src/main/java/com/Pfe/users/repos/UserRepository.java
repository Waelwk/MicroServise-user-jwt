package com.Pfe.users.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pfe.users.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
Users findByUsername(String username);
}
