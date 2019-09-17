package it.smartcommunitylab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import it.smartcommunitylab.domain.Product;
import it.smartcommunitylab.domain.Purchase;
import it.smartcommunitylab.domain.RequestPurchase;
import it.smartcommunitylab.purchase.Service;
import it.smartcommunitylab.rep.PurchaseRep;

@Controller
public class ControllerPurchase {
	@Autowired
	private Service service;
	
	@GetMapping("/api/purchased/{userId}")
	public @ResponseBody Page<Purchase> listPurchases(@PathVariable String userId, Pageable pageRequest) {
		return service.getUserPurchases(userId, pageRequest);
	}
	
	@GetMapping("/api/purchases/purchase/{purchasedId}")
	public @ResponseBody Purchase getPurchased(@PathVariable String purchasedId) {
		return service.getUserPurchase(purchasedId);
	}
	
	@PostMapping("/api/purchased/{userId}")
	public @ResponseBody Purchase buy(@PathVariable String userId, @RequestBody RequestPurchase request) {
		return service.buy(userId, request);
	}
	
}
