package com.quycntt.repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.quycntt.domain.Category;
import com.quycntt.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	Product findById(int id);
	
	@Query("select p from Product p order by joindate desc")
	List<Product> findProductLimit(Pageable pageable);
	
//	@Query("select p from Product p order by joindate desc limit :limit1 offset :start")
//	List<Product> findProductPaging(@Param ("limit1") int limit, @Param("start") Integer start);
	
	@Transactional
	@Modifying
	@Query("delete from Product p where p.id = :id")
	void deleteProduct(@Param("id") Integer id );
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%')")
	List<Product> searchProduct(@Param("name") String name);
	
	@Query("from Product p where p.category = :category")
	List<Product> findByCategoryId(@Param("category") Category category);
	
}
