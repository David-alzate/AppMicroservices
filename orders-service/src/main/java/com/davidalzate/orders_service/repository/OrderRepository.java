package com.davidalzate.orders_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidalzate.orders_service.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
