package com.commerce.ecommerce_api.service;

import com.commerce.ecommerce_api.dto.request.OrderRequest;
import com.commerce.ecommerce_api.dto.response.OrderResponse;
import com.commerce.ecommerce_api.model.Orderstatus;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse updateOrderStatus(Long id, Orderstatus status);
    void deleteOrder(Long id);

}
