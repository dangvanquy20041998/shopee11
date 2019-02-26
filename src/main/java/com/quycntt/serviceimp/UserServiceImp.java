package com.quycntt.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quycntt.domain.User;
import com.quycntt.repository.UserRepository;
import com.quycntt.service.UserService;

@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

}
