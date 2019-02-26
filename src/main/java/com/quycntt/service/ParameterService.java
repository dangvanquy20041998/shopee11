package com.quycntt.service;

import org.springframework.stereotype.Service;

import com.quycntt.domain.Parameter;

@Service
public interface ParameterService {
	Parameter findById(int id);
}
