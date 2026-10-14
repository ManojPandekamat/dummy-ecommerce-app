package com.demo.api;


import org.springframework.web.bind.annotation.*;

import com.demo.DTO.OrderDTO;
import com.demo.DTO.UserDTO;
import com.demo.service.OrderService;
import com.demo.service.UserService;

import java.util.List;

@RestController
public class UserAPI {

    private final UserService userService;
    private final OrderService orderService;

    public UserAPI(UserService userService,OrderService oservice) {
        this.userService = userService;
        this.orderService=oservice;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id)
            ;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders() {
        return orderService.getOrders();
    }

}