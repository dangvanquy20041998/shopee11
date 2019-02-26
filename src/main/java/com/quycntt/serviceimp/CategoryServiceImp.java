package com.quycntt.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quycntt.domain.Category;
import com.quycntt.repository.CategoryRepository;
import com.quycntt.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(int id) {
		return categoryRepository.findById(id);
	}
}
