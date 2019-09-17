package it.smartcommunitylab.service;

import org.springframework.stereotype.Component;

import it.smartcommunitylab.domain.Product;

@Component
public class ProductServiceFallback implements ProductService{
	
	@Override
	public Product getProduct(String productId) {
		return new Product();
	}
	
	@Override
	public Product bookAvailability(String productId, int quantity) {
		return new Product();
	}
}
