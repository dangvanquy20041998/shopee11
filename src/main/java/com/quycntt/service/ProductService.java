package com.quycntt.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.quycntt.domain.Category;
import com.quycntt.domain.Product;

public interface ProductService {
	
	Iterable<Product> findAll();
	
	Product findById(int id);
	
	void save(Product product);
	
	void delete(Product product);
	
	List<Product> findProductLimit(Pageable pageable);
	
	void deleteProduct(int id);
	
	List<Product> searchProduct(String name);
	
	List<Product> findByCategoryId(Category category);
//	List<Product> findProductPaging(int limit, int start);
}
