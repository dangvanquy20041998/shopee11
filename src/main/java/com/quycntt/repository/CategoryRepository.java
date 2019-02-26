package com.quycntt.repository;

import org.springframework.data.repository.CrudRepository;

import com.quycntt.domain.Category;
import com.quycntt.domain.Product;

public interface CategoryRepository extends CrudRepository<Category, Integer>{ 
	Category findById(int id);
}
