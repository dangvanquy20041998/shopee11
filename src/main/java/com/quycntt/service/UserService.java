package com.quycntt.service;

import com.quycntt.domain.User;

public interface UserService {
	User findByEmail(String email);
	void save(User user);
}
