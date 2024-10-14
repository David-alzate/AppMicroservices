package com.davidalzate.inventory_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidalzate.inventory_service.model.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	 Optional<Inventory> findBySku(String sku);
	 
	 List<Inventory> findBySkuIn(List<String> skus);

}
