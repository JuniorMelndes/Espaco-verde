package com.espacoverde.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espacoverde.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findByIdProduct(Long idProduct);
	
	Product findByName(String productName);
}
