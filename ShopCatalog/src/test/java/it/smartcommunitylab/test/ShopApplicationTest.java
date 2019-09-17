package it.smartcommunitylab.test;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.smartcommunitylab.data.ProductRepository;
import it.smartcommunitylab.domain.Product;
import it.smartcommunitylab.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopApplicationTest {
	@Autowired
	private ProductRepository repo;
	@Autowired
	public MockMvc mockMvc;
	
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

		RequestBuilder postRequest = MockMvcRequestBuilders.post("/api/products")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(product));
		mockMvc.perform(postRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("title", Matchers.is("title")));
		
		RequestBuilder listRequest = MockMvcRequestBuilders.get("/api/products")
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		mockMvc.perform(listRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title", Matchers.is("title")));
		
		}
}
