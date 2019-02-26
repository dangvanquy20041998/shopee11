package com.quycntt.repository;

import org.springframework.data.repository.CrudRepository;
import com.quycntt.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByName(String name);
	
}
