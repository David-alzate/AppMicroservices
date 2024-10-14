package com.davidalzate.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidalzate.inventory_service.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
