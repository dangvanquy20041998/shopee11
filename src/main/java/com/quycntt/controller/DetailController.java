package com.quycntt.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.quycntt.domain.Cart;
import com.quycntt.domain.Comment;
import com.quycntt.domain.Product;
import com.quycntt.domain.Role;
import com.quycntt.domain.User;
import com.quycntt.repository.UserRepository;
import com.quycntt.serviceimp.CategoryServiceImp;
import com.quycntt.serviceimp.CommentServiceImp;
import com.quycntt.serviceimp.ParameterServiceImp;
import com.quycntt.serviceimp.ProductServiceImp;

@Controller
@SessionAttributes("cart")
public class DetailController {
	
	@Autowired
	private ProductServiceImp productServiceImp;
	
	@Autowired
	private CategoryServiceImp categoryServiceImp;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ParameterServiceImp parameterServiceImp;
	
	@Autowired
	private CommentServiceImp commentServiceImp;
	
	private Product product;
	
	@GetMapping("/detail")
	public String detail(Principal principal, @RequestParam int id, ModelMap modelMap, HttpSession httpSession) {
		modelMap.addAttribute("product", productServiceImp.findById(id));
		modelMap.addAttribute("categories", categoryServiceImp.findAll());
		modelMap.addAttribute("parameter", parameterServiceImp.findById(id));
		
		
		modelMap.addAttribute("sizeCart", listCart.size());
		
		product = productServiceImp.findById(id);
		
		modelMap.addAttribute("comments", commentServiceImp.findAllCommentById(product));
		if (principal != null) {
			User user = userRepository.findByEmail(principal.getName());
			Set<Role> roles = user.getRoles();
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("roles", roles);
		}
		
		return "detail";
	}
	
	private List<Cart> listCart = new ArrayList<Cart>();
 	
	@GetMapping("/addCart")
	@ResponseBody
	public void addCart(@RequestParam int id, HttpSession httpSession) {
		Product product = productServiceImp.findById(id);
		Cart cart = new Cart(); 
		cart.setId(id);
		cart.setName(product.getName());
		cart.setPrice(product.getPrice());
		cart.setQuantity(1);
		
		if (httpSession.getAttribute("cart") == null) {
			listCart.add(cart);
		} else {
			int idCheck = check(id, httpSession);
			if (idCheck == -1) {
				listCart.add(cart);
			} else {
				List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
				int quantityNew = listCart.get(idCheck).getQuantity() + 1;
				listCart.get(idCheck).setQuantity(quantityNew);
			}
		}
		
		httpSession.setAttribute("cart", listCart);
		
	}
	
	@PostMapping("/createComment")
	public String comment(Principal principal, @RequestParam String description) {
		if (principal != null) {
			User user = userRepository.findByEmail(principal.getName());
			Comment comment = new Comment();
			comment.setDescription(description);
			comment.setUser(user);
			comment.setProduct(product);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			comment.setJoindate(dateFormat.format(date));
			commentServiceImp.save(comment);
		}
		return "redirect:/";
	}
	
//	@GetMapping("/getIdComment")
//	@ResponseBody
//	public String getIdComment(@RequestParam int id) {
//		System.out.println(id);
//		return "redirect:/";
//	}
	
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
