package com.three.oneshort.repository;

import com.three.oneshort.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}