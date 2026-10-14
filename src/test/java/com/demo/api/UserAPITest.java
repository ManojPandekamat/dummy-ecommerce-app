package com.demo.api;

import com.demo.DTO.OrderDTO;
import com.demo.DTO.UserDTO;
import com.demo.service.OrderService;
import com.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAPITest {

    @Mock
    private UserService userService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private UserAPI userAPI;

    @Test
    void getUsersDelegatesToUserService() {
        List<UserDTO> users = List.of(new UserDTO(1L, "Ava", "ava@example.com", List.of()));
        when(userService.getUsers()).thenReturn(users);

        assertSame(users, userAPI.getUsers());
        verify(userService).getUsers();
    }

    @Test
    void getUserDelegatesIdToUserService() {
        UserDTO user = new UserDTO(2L, "Noah", "noah@example.com", List.of());
        when(userService.getUser(2L)).thenReturn(user);

        assertSame(user, userAPI.getUser(2L));
        verify(userService).getUser(2L);
    }

    @Test
    void getOrdersDelegatesToOrderService() {
        List<OrderDTO> orders = List.of(
                new OrderDTO(10L, "Keyboard", new BigDecimal("49.99"), 1L));
        when(orderService.getOrders()).thenReturn(orders);

        assertSame(orders, userAPI.getOrders());
        verify(orderService).getOrders();
    }
}
