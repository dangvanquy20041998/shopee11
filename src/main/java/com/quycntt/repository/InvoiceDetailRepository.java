package com.quycntt.repository;

import org.springframework.data.repository.CrudRepository;

import com.quycntt.domain.InvoiceDetail;

public interface InvoiceDetailRepository extends CrudRepository<InvoiceDetail, Integer> {
	
}
