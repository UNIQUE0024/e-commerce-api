package com.company.ecommerce.repository;

import com.company.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository {
    
    List findByCategory(String category);
    
    List findByNameContaining(String name);
    
    List findByPriceBetween(Double minPrice, Double maxPrice);
}
