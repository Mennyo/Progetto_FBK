package it.smartcommunitylab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import it.smartcommunitylab.domain.Product;

@FeignClient(name ="shopcatalog", fallback=ProductServiceFallback.class)
public interface ProductService {
	
	@GetMapping("/api/products/{productId}")
	public Product getProduct(@PathVariable String productId);
	
	@PutMapping("/api/products/{productId}/avaiability/{quantity}")
	public Product bookAvailability(@PathVariable String productId, @PathVariable int quantity);

	//@Service
	//public class ProductService {
		//@Autowired
		//private RestTemplate restTemplate;
		
		//public Product getProduct(String productId) {
			//return restTemplate.getForObject("http://shoppurchase/api/products/{productId}", Product.class, productId);
		//}
		
		//public Product bookAvailability(String productId, int quantity) {
			//return restTemplate.exchange("http://shoppurchase/api/products/{productsId}/availability/{quantity}", HttpMethod.PUT, null, Product.class, productId, -quantity).getBody();
		//}
	
}

