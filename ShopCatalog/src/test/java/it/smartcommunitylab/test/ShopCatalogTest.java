package it.smartcommunitylab.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import it.smartcommunitylab.data.ProductRepository;
import it.smartcommunitylab.domain.Product;
import it.smartcommunitylab.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCatalogTest {
	@Autowired
	private ProductRepository repo;
	@Autowired
	public ProductService service;
	
	@org.junit.Before
	public void tearUp() {
		repo.deleteAll();
	}
	
	@Test
	public void testData() throws Exception {
		Product product = new Product();
		product.setAvailability(10);
		product.setCategory("test");
		product.setTitle("title");
		product.setDescription("description");
		product.setPrice(100.0);

		product = service.create(product);
		Assert.assertNotNull(product.getId());
		
		product = service.getProduct(product.getId());
		Assert.assertNotNull(product);
		
		
		Page<Product> page = service.getProducts(PageRequest.of(0, 10));
		Assert.assertEquals(1, page.getContent().size());
		
		page = service.getProductByCategory("test", PageRequest.of(0, 10));
		Assert.assertEquals(1, page.getContent().size());
		
		service.changeAvailability(product.getId(), -1);
		product = service.getProduct(product.getId());
		Assert.assertEquals(9, (int)product.getAvailability());
		}
}
