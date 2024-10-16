package com.davidalzate.orders_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.davidalzate.orders_service.model.dtos.BaseResponse;
import com.davidalzate.orders_service.model.dtos.OrderItemsRequest;
import com.davidalzate.orders_service.model.dtos.OrderItemsResponse;
import com.davidalzate.orders_service.model.dtos.OrderRequest;
import com.davidalzate.orders_service.model.dtos.OrderResponse;
import com.davidalzate.orders_service.model.entities.Order;
import com.davidalzate.orders_service.model.entities.OrderItems;
import com.davidalzate.orders_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;

	public void placeOrder(OrderRequest orderRequest) {
		
		//Check for inventory
		BaseResponse result = this.webClientBuilder.build()
		.post()
		.uri("http://localhost:8083/api/inventory/in-stock")
		.bodyValue(orderRequest.getOrderItems())
		.retrieve()
		.bodyToMono(BaseResponse.class)
		.block();
		
		if(result != null && !result.hasError()) {
			Order order = new Order();
			order.setOrderNumber(UUID.randomUUID().toString());
			order.setOrderItems(orderRequest.getOrderItems().stream().map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order)).toList());
			this.orderRepository.save(order);
		}else {
			throw new IllegalArgumentException("Some of the products are not in stock");
		}
	}
	
	public List<OrderResponse> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();

        return orders.stream().map(this::mapToOrderResponse).toList();

    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getOrderNumber()
                , order.getOrderItems().stream().map(this::mapToOrderItemRequest).toList());
    }

    private OrderItemsResponse mapToOrderItemRequest(OrderItems orderItems) {
        return new OrderItemsResponse(orderItems.getId(), orderItems.getSku(), orderItems.getPrice(), orderItems.getQuantity());
    }

	private OrderItems mapOrderItemRequestToOrderItem(OrderItemsRequest orderItemsRequest, Order order) {
		return OrderItems.builder().id(orderItemsRequest.getId()).sku(orderItemsRequest.getSku())
				.price(orderItemsRequest.getPrice()).quantity(orderItemsRequest.getQuantity()).order(order).build();
	}

}
