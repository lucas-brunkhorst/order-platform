package com.orderplatform.orderservice.service;

import com.orderplatform.orderservice.dto.OrderItemResponseDTO;
import com.orderplatform.orderservice.dto.OrderRequestDTO;
import com.orderplatform.orderservice.dto.OrderResponseDTO;
import com.orderplatform.orderservice.model.Order;
import com.orderplatform.orderservice.model.OrderItem;
import com.orderplatform.orderservice.model.OrderStatus;
import com.orderplatform.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        var order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setPublicId(UUID.randomUUID());

        List<OrderItem> items = request.items().stream().map(itemDTO -> {
            OrderItem item = new OrderItem();
            item.setProductId(itemDTO.productId());
            item.setQuantity(itemDTO.quantity());
            item.setOrder(order);
            return item;
        }).toList();

        order.setItems(items);
        Order saved = orderRepository.save(order);

        return toResponseDTO(saved);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        List<OrderItemResponseDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemResponseDTO(item.getProductId(), item.getQuantity()))
                .toList();

        return new OrderResponseDTO(order.getPublicId(), order.getStatus(), itemDTOs);
    }
}
