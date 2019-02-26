package com.quycntt.repository;

import org.springframework.data.repository.CrudRepository;

import com.quycntt.domain.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer>{
	
}
