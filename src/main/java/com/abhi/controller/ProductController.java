package com.abhi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhi.entity.Product;
import com.abhi.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/edit")
	public String edit(@RequestParam("pid") Integer pid,Model model) {
		Optional<Product> findById = repo.findById(pid);
		if(findById.isPresent()) {
			Product product = findById.get();
			model.addAttribute("product", product);
			//we tale as a hidden data to edit the existing data
		}
		
		return "index";
		
	}
	
	@GetMapping("/delete")
	//bring ui data to backend we use @requestParam annotation
    public String delete(@RequestParam("pid") Integer pid, Model model) {
		repo.deleteById(pid);
		//to add massage in the console we use this
		model.addAttribute("msg", "Product Is Deleted");
		//to get latest data we use this
		model.addAttribute("products", repo.findAll());
		return "data";
    	
    }
	
	@GetMapping("/products")
	public String getAllProducts(Model model) {
		List<Product> findAll = repo.findAll();
		model.addAttribute("products", findAll);
		return "data";
	}
	

	// save the data into database
	@PostMapping("/product")
	//it is use to validate binding class obj data@Validated.
	//bindingResult is use to give the details that our validation are fail or not.
	public String saveProduct(@Validated @ModelAttribute("product") Product p, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "index";
		}
		
		p = repo.save(p);
		if (p.getPid() != null)
			model.addAttribute("msg", "Product saved");
		return "index";
	}

//load my page with empty object 
	@GetMapping("/")
	public String loadFaom(Model model) {
		model.addAttribute("product", new Product());
		return "index";

	}

}
