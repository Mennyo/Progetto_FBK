package it.elisa.purchase.domain;

public class PurchaseRequest {

	private String productId;
	private Integer count;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
		
	
}
