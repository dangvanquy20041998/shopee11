package com.quycntt.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.quycntt.domain.Cart;
import com.quycntt.domain.Invoice;
import com.quycntt.domain.InvoiceDetail;
import com.quycntt.domain.Product;
import com.quycntt.domain.Role;
import com.quycntt.domain.User;
import com.quycntt.repository.UserRepository;
import com.quycntt.service.InvoiceDetailService;
import com.quycntt.serviceimp.CategoryServiceImp;
import com.quycntt.serviceimp.InvoiceServiceImp;
import com.quycntt.serviceimp.ProductServiceImp;

@Controller
@SessionAttributes("cart")
public class CartController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryServiceImp categoryServiceImp;
	
	@Autowired
	private ProductServiceImp productServiceImp;
	
	@Autowired
	private InvoiceServiceImp invoiceServiceImp;
	
	@Autowired
	private InvoiceDetailService invoiceDetailServiceImp;
	
	@GetMapping("/cart")
	public String cart(Principal principal, ModelMap modelMap, HttpSession httpSession) {
		if (null != httpSession.getAttribute("cart")) {
			List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
			modelMap.addAttribute("listCart", listCart);
		}
		
		if (principal != null) {
			User user = userRepository.findByEmail(principal.getName());
			modelMap.addAttribute("user", user);
		}
		
		modelMap.addAttribute("categories", categoryServiceImp.findAll());
		
		return "cart";
	}
	
	@GetMapping("/deleteCart")
	public String delete(Integer id, HttpSession httpSession) {
		List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
		int idCheck = check(id, httpSession);
		if (idCheck != -1) {
			listCart.remove(idCheck);
		}
		return "redirect:/cart";
	}
	
	@PostMapping("/createInvoice")
	public String addInvoice(@RequestParam String name, @RequestParam String phone,
			 				 @RequestParam String address, @RequestParam String choose, @RequestParam String noted, HttpSession httpSession) {
		
		Invoice invoice = new Invoice();
		invoice.setName(name);
		invoice.setPhone(phone);
		invoice.setAddress(address);
		invoice.setDelivery(choose);
		invoice.setNote(noted);
		
		invoiceServiceImp.save(invoice);
		
		List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
		
		for (Cart cart : listCart) {
			Product product = productServiceImp.findById(cart.getId());
			InvoiceDetail invoiceDetail = new InvoiceDetail();
			invoiceDetail.setInvoice(invoice);
			invoiceDetail.setProduct(product);
			invoiceDetail.setPrice(cart.getPrice());
			invoiceDetail.setQuantity(cart.getQuantity());
			
			invoiceDetailServiceImp.save(invoiceDetail);
		}
		listCart.removeAll(listCart);
		return "cart";
	}
	
	private int check(int id, HttpSession httpSession) {
		List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
		for (int i = 0; i < listCart.size(); i++) {
			if (listCart.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
