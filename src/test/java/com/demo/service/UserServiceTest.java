package com.demo.service;

import com.demo.DTO.OrderDTO;
import com.demo.DTO.UserDTO;
import com.demo.entity.Order;
import com.demo.entity.Users;
import com.demo.reository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUsersMapsUsersAndTheirOrders() {
        Users user = user(1L, "Ava", "ava@example.com");
        Order order = order(10L, "Keyboard", "49.99", user);
        user.setOrders(List.of(order));
        Users userWithoutOrders = user(2L, "Noah", "noah@example.com");
        userWithoutOrders.setOrders(List.of());
        when(userRepository.findAll()).thenReturn(List.of(user, userWithoutOrders));

        List<UserDTO> result = userService.getUsers();

        assertEquals(2, result.size());
        assertEquals(new UserDTO(1L, "Ava", "ava@example.com",
                List.of(new OrderDTO(10L, "Keyboard", new BigDecimal("49.99"), 1L))), result.get(0));
        assertEquals(new UserDTO(2L, "Noah", "noah@example.com", List.of()), result.get(1));
        verify(userRepository).findAll();
    }

    @Test
    void getUsersReturnsEmptyListWhenRepositoryHasNoUsers() {
        when(userRepository.findAll()).thenReturn(List.of());

        assertEquals(List.of(), userService.getUsers());
        verify(userRepository).findAll();
    }

    @Test
    void getUserMapsUserAndOrders() {
        Users user = user(3L, "Mia", "mia@example.com");
        user.setOrders(List.of(order(30L, "Mouse", "25.00", user)));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUser(3L);

        assertEquals(new UserDTO(3L, "Mia", "mia@example.com",
                List.of(new OrderDTO(30L, "Mouse", new BigDecimal("25.00"), 3L))), result);
        verify(userRepository).findById(3L);
    }

    @Test
    void getUserThrowsWhenUserDoesNotExist() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> userService.getUser(99L));
        verify(userRepository).findById(99L);
    }

    private static Users user(Long id, String name, String email) {
        Users user = new Users();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        return user;
    }

    private static Order order(Long id, String product, String amount, Users user) {
        Order order = new Order(product, new BigDecimal(amount), user);
        order.setId(id);
        return order;
    }
}
