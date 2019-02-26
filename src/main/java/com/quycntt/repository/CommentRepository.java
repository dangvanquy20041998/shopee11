package com.quycntt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.quycntt.domain.Comment;
import com.quycntt.domain.Product;

public interface CommentRepository extends CrudRepository<Comment, Integer>{
	@Query("select c from Comment c where c.product = :product")
	List<Comment> findCommentById(@Param("product") Product product);

}
