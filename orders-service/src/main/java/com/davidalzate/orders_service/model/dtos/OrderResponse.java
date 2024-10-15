package com.davidalzate.orders_service.model.dtos;

import java.util.List;

public record OrderResponse(Long id, String OrderNumber, List<OrderItemsResponse> orderItems) {

}
