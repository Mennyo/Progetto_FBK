package it.smartcommunitylab.test;

import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import it.smartcommunitylab.data.ProductRepository;
import it.smartcommunitylab.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopContextTest {
	@Autowired
	private ProductRepository repo;
	
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

		product = repo.save(product);
		Assert.assertNotNull(product.getId());
		
		Optional<Product> optional	= repo.findById(product.getId());
		Assert.assertTrue(optional.isPresent());
		
		List<Product> list = repo.findAll();
		Assert.assertEquals(1, list.size());
		
		Page<Product> page = repo.findByCategory("test", PageRequest.of(0, 10));
		Assert.assertEquals(1, page.getContent().size());
		
		repo.delete(product);
		optional = repo.findById(product.getId());
		Assert.assertTrue(!optional.isPresent());
		}
}
