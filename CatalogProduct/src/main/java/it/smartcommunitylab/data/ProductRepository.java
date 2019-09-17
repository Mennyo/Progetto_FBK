package it.smartcommunitylab.data;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.smartcommunitylab.domain.Product;

import org.springframework.data.domain.Pageable;

public interface ProductRepository extends MongoRepository<Product, String> {
	Page<Product> findByCategory(String category, Pageable pageRequest);
}
