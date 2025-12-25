package com.company.ecommerce.controller;

import com.company.ecommerce.model.Product;
import com.company.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductService productService;
    
    private Product product1;
    private Product product2;
    
    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setDescription("Gaming Laptop");
        product1.setPrice(new BigDecimal("1299.99"));
        product1.setStock(10);
        product1.setCategory("Electronics");
        
        product2 = new Product();
        product2.setId(2L);
        product2.setName("Mouse");
        product2.setDescription("Wireless Mouse");
        product2.setPrice(new BigDecimal("29.99"));
        product2.setStock(50);
        product2.setCategory("Electronics");
    }
    
    @Test
    void shouldReturnAllProducts() throws Exception {
        List products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);
        
        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].name").value("Laptop"))
            .andExpect(jsonPath("$[1].name").value("Mouse"));
    }
    
    @Test
    void shouldReturnProductById() throws Exception {
        when(productService.getProductById(1L))
            .thenReturn(Optional.of(product1));
        
        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Laptop"))
            .andExpect(jsonPath("$.price").value(1299.99));
    }
    
    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {
        when(productService.getProductById(999L))
            .thenReturn(Optional.empty());
        
        mockMvc.perform(get("/api/products/999"))
            .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldCreateProduct() throws Exception {
        when(productService.createProduct(any(Product.class)))
            .thenReturn(product1);
        
        String productJson = """
            {
                "name": "Laptop",
                "description": "Gaming Laptop",
                "price": 1299.99,
                "stock": 10,
                "category": "Electronics"
            }
            """;
        
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Laptop"));
    }
}
