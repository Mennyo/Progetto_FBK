package it.smartcommunitylab.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.smartcommunitylab.domain.Product;
import it.smartcommunitylab.domain.Purchase;
import it.smartcommunitylab.domain.RequestPurchase;
import it.smartcommunitylab.rep.PurchaseRep;
import it.smartcommunitylab.service.ProductService;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	private PurchaseRep repo;
	
	@Autowired
	private ProductService productService;
	
	public Page<Purchase> getUserPurchases(String userId, Pageable pageRequest) {
		return repo.findByUserId(userId, pageRequest);
	}
	
	public Purchase getUserPurchase(String purchaseId) {
		return repo.findById(purchaseId).orElse(null);
	}
	
	
	public Purchase buy (String userId, RequestPurchase request) {
		Purchase purchase = new Purchase();
		
		Product product = productService.getProduct(request.getProductId());
		if (product != null) {
			purchase.setProductId(request.getProductId());
			purchase.setQuantity(request.getCount());
			purchase.setUserId(userId);
			purchase.setTitle(product.getTitle());
			purchase.setCategory(product.getCategory());
			purchase.setPrice(product.getPrice());
			
			productService.bookAvailability(request.getProductId(), request.getCount());
			return repo.save(purchase);
		} else {
			return purchase;
		}
		
	}
	
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "test";
	}

}
