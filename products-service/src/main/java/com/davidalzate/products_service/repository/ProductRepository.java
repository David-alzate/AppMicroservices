package com.davidalzate.products_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidalzate.products_service.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
