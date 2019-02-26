package com.quycntt.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quycntt.domain.InvoiceDetail;
import com.quycntt.repository.InvoiceDetailRepository;
import com.quycntt.service.InvoiceDetailService;

@Service
public class InvoiceDetailServiceImp implements InvoiceDetailService{
	
	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;
	@Override
	public void save(InvoiceDetail invoiceDetail) {
		invoiceDetailRepository.save(invoiceDetail);
	}

}
