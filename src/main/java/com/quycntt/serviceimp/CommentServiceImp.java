package com.quycntt.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quycntt.domain.Comment;
import com.quycntt.domain.Product;
import com.quycntt.repository.CommentRepository;
import com.quycntt.service.CommentService;

@Service
public class CommentServiceImp implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public void save(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public List<Comment> findAllCommentById(Product product) {
		return commentRepository.findCommentById(product);
	}
}
