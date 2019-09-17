package it.smartcommunitylab.rep;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.smartcommunitylab.domain.Purchase;

public interface PurchaseRep extends MongoRepository<Purchase, String> {
	Page<Purchase> findByUserId(String userId, Pageable pageRequest);
}

