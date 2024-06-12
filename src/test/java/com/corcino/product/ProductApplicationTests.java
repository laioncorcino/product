package com.corcino.product;

import com.corcino.product.controller.ProductController;
import com.corcino.product.handler.GlobalExceptionHandler;
import com.corcino.product.json.ProductRequest;
import com.corcino.product.json.ProductResponse;
import com.corcino.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApplicationTests {

	@Autowired MockMvc mockMvc;
	@MockBean ProductService productDomainService;
	@InjectMocks ProductController productController;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController)
				.setControllerAdvice(new GlobalExceptionHandler())
				.build();
	}

	@Test
	public void testPost() throws Exception {

		ProductRequest request = new ProductRequest();
		request.setName("Test Product");
		request.setPrice(100.0);
		request.setQuantity(10);
		request.setCategoryId(1L);

		ProductResponse response = new ProductResponse();
		response.setName(request.getName());
		response.setPrice(request.getPrice());
		response.setQuantity(request.getQuantity());

		when(productDomainService.create(any(ProductRequest.class)))
				.thenReturn(response);

		mockMvc.perform(post("/api/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(request)))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testPut() throws Exception {

		ProductRequest request = new ProductRequest();
		request.setName("Update Product");
		request.setPrice(150.0);
		request.setQuantity(10);
		request.setCategoryId(2L);

		ProductResponse response = new ProductResponse();
		response.setName(request.getName());
		response.setPrice(request.getPrice());
		response.setQuantity(request.getQuantity());

		when(productDomainService.update(eq(1L), any(ProductRequest.class)))
				.thenReturn(response);

		mockMvc.perform(put("/api/products/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(request)))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testDelete() throws Exception {

		ProductResponse response = new ProductResponse();

		when(productDomainService.delete(eq(1L)))
				.thenReturn(response);

		mockMvc.perform(delete("/api/products/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testGetAll() throws Exception {

		ProductResponse response1 = new ProductResponse();
		response1.setName("Product 1");
		response1.setPrice(100.0);

		ProductResponse response2 = new ProductResponse();
		response1.setName("Product 2");
		response1.setPrice(200.0);

		List<ProductResponse> list = Arrays.asList(response1, response2);

		when(productDomainService.getAll(any(Pageable.class)))
				.thenReturn(list);

		mockMvc.perform(get("/api/products")
						.param("page", "0")
						.param("size", "10")
						.param("sortBy", "id")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testGetById() throws Exception {

		ProductResponse response = new ProductResponse();
		response.setName("Product 1");
		response.setPrice(100.0);

		when(productDomainService.getById(1L))
				.thenReturn(response);

		mockMvc.perform(get("/api/products/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

}

