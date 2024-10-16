package com.davidalzate.orders_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.davidalzate.orders_service.model.dtos.OrderRequest;
import com.davidalzate.orders_service.model.dtos.OrderResponse;
import com.davidalzate.orders_service.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		this.orderService.placeOrder(orderRequest);
		return "Order Placed Successfully";
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponse> getOrders() {
		return this.orderService.getAllOrders();
	}

}
