package com.quycntt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.quycntt.domain.Category;
import com.quycntt.domain.Product;
import com.quycntt.serviceimp.CategoryServiceImp;
import com.quycntt.serviceimp.ProductServiceImp;

@Controller
public class AdminController {
	
	@Autowired
	private CategoryServiceImp categoryServiceImp;
	
	@Autowired
	private ProductServiceImp productServiceImp;
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images/";
	
	@GetMapping("/product")
	public String product(@RequestParam(defaultValue="1") int page, ModelMap modelMap) {
		Iterable<Product> products = productServiceImp.findAll();
		double size = 0;
		
		if (products instanceof Collection<?>) {
			  size =  ((Collection<?>)products).size();
		}
		
		double paging = Math.ceil(size / 5);
		int start = 0;
		
		if (page > 0) {
			start = (page -1);
		}
		
		modelMap.addAttribute("categories", categoryServiceImp.findAll());
		modelMap.addAttribute("products", productServiceImp.findProductLimit(new PageRequest(start, 5)));
		modelMap.addAttribute("paging", paging);
		modelMap.addAttribute("pageNumber", page);
		return "product";
	}
	
	@PostMapping("/create")
	  public String create(@RequestParam String name, @RequestParam String price, @RequestParam String description, @RequestParam int idcategory, @RequestParam("files") MultipartFile file) {
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		String fileName = "/images/" + file.getOriginalFilename();
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		Category category1 = new Category();
		category1 = categoryServiceImp.findById(idcategory);
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setImage(fileName);
		product.setJoindate(dateFormat.format(date));
		product.setCategory(category1);
		
		productServiceImp.save(product);
		  
		return "redirect:/product";
	}
	
	@PostMapping("/update")
	  public String update(@RequestParam int id, @RequestParam String name, @RequestParam String price, @RequestParam String description, @RequestParam int idcategory, @RequestParam("files") MultipartFile file) {
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		String fileName = "/images/" + file.getOriginalFilename();
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		Category category1 = new Category();
		category1 = categoryServiceImp.findById(idcategory);
		
		Product product = productServiceImp.findById(id);
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setImage(fileName);
		product.setJoindate(dateFormat.format(date));
		product.setCategory(category1);
		
		productServiceImp.save(product);
		return "redirect:/product";
	}
	
	@GetMapping("/findOne")
	@ResponseBody
	public Product findOne(Integer id) {
		return productServiceImp.findById(id);
	}
	
	@GetMapping("/delete")
	public String delete(Integer id) {
		Product product = new Product();
		product = productServiceImp.findById(id);
		productServiceImp.deleteProduct(id);
		return "redirect:/product";
	}
}
