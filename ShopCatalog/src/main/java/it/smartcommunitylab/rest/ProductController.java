package it.smartcommunitylab.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.smartcommunitylab.domain.Product;
import it.smartcommunitylab.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "test";
	}
	
	@GetMapping("/api/products")
	public @ResponseBody Page<Product> listProducts(Pageable pageRequest) {
		return service.getProducts(pageRequest);
	}
	
	@GetMapping("/api/products/{productId}")
	public @ResponseBody Product getProduct(@PathVariable String productId) {
		return service.getProduct(productId);
	}
	
	@GetMapping("/api/products/category{category}")
	public @ResponseBody Page<Product> listProduct(@PathVariable String category, Pageable pageRequest) {
		return service.getProductByCategory(category, pageRequest);
	}
	
	@PostMapping("/api/products")
	public @ResponseBody Product create(@RequestBody Product product) {
		return service.create(product);
	}
	
	@PutMapping("api/products/{productsId}/availability/{value}")
	public @ResponseBody Product changeAvailability(@PathVariable String productsId, @PathVariable int value ) {
		return service.changeAvailability(productsId, value);
	}
	
}
