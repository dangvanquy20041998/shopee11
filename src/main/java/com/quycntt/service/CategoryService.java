package com.quycntt.service;

import com.quycntt.domain.Category;

public interface CategoryService {
	Iterable<Category> findAll();
	Category findById(int id);
}
