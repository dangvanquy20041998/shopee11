package com.quycntt.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quycntt.domain.Role;
import com.quycntt.repository.RoleRepository;
import com.quycntt.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
