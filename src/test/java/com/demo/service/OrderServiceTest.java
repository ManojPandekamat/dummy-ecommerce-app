package com.demo.service;

import com.demo.DTO.OrderDTO;
import com.demo.entity.Order;
import com.demo.entity.Users;
import com.demo.reository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void getOrdersMapsOrdersToDtos() {
        Users firstUser = user(1L);
        Users secondUser = user(2L);
        Order firstOrder = order(10L, "Keyboard", "49.99", firstUser);
        Order secondOrder = order(11L, "Mouse", "25.00", secondUser);
        when(orderRepository.findAll()).thenReturn(List.of(firstOrder, secondOrder));

        List<OrderDTO> result = orderService.getOrders();

        assertEquals(List.of(
                new OrderDTO(10L, "Keyboard", new BigDecimal("49.99"), 1L),
                new OrderDTO(11L, "Mouse", new BigDecimal("25.00"), 2L)
        ), result);
        verify(orderRepository).findAll();
    }

    @Test
    void getOrdersReturnsEmptyListWhenRepositoryHasNoOrders() {
        when(orderRepository.findAll()).thenReturn(List.of());

        assertEquals(List.of(), orderService.getOrders());
        verify(orderRepository).findAll();
    }

    private static Users user(Long id) {
        Users user = new Users();
        user.setId(id);
        return user;
    }

    private static Order order(Long id, String product, String amount, Users user) {
        Order order = new Order(product, new BigDecimal(amount), user);
        order.setId(id);
        return order;
    }
}
