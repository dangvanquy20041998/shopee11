package com.quycntt.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quycntt.domain.Parameter;
import com.quycntt.repository.ParameterRepository;
import com.quycntt.service.ParameterService;

@Service
public class ParameterServiceImp implements ParameterService{
	
	@Autowired
	private ParameterRepository parameterRepository;
	
	@Override
	public Parameter findById(int id) {
		return parameterRepository.findById(id);
	}
}
