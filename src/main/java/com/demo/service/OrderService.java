package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.DTO.OrderDTO;
import com.demo.reository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getProduct(),
                        order.getAmount(),
                        order.getUser().getId()
                ))
                .toList();
    }
}