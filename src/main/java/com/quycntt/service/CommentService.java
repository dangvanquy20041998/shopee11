package com.quycntt.service;

import java.util.List;

import com.quycntt.domain.Comment;
import com.quycntt.domain.Product;

public interface CommentService {
	void save(Comment comment);
	List<Comment> findAllCommentById(Product product);
}
