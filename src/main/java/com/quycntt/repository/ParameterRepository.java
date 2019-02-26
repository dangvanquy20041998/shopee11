package com.quycntt.repository;

import org.springframework.data.repository.CrudRepository;

import com.quycntt.domain.Parameter;

public interface ParameterRepository extends CrudRepository<Parameter, Integer>{
	Parameter findById(int id);
}
