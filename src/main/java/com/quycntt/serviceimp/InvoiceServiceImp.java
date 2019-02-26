package com.quycntt.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quycntt.domain.Invoice;
import com.quycntt.repository.InvoiceRepository;
import com.quycntt.service.InvoiceService;

@Service 
public class InvoiceServiceImp implements InvoiceService{
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public void save(Invoice invoice) {
		invoiceRepository.save(invoice);
	}
}
