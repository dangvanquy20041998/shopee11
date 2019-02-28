package com.quycntt.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quycntt.domain.Category;
import com.quycntt.domain.Product;
import com.quycntt.repository.ProductRepository;
import com.quycntt.service.ProductService;

@Service
public class ProductServiceImp implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(int id) {
		return productRepository.findById(id);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> findProductLimit(Pageable pageable) {
		return productRepository.findProductLimit(pageable);
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteProduct(id);
	}

	@Override
	public List<Product> searchProduct(String name) {
		return productRepository.findByNameContaining(name);
	}

	@Override
	public List<Product> findByCategoryId(Category category) {
		return productRepository.findByCategoryId(category);
	}

//	@Override
//	public List<Product> findProductPaging(int limit, int start) {
//		return productRepository.findProductPaging(limit, start);
//	}
}
