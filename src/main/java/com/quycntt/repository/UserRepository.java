package com.quycntt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.quycntt.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);
}
