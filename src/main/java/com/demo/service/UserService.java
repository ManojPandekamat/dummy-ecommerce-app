package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.DTO.OrderDTO;
import com.demo.DTO.UserDTO;
import com.demo.entity.Users;
import com.demo.reository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getOrders()
                                .stream()
                                .map(order -> new OrderDTO(
                                        order.getId(),
                                        order.getProduct(),
                                        order.getAmount(),
                                        order.getUser().getId()
                                ))
                                .toList()
                ))
                .toList();
    }

    public UserDTO getUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow();

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getOrders()
                        .stream()
                        .map(order -> new OrderDTO(
                                order.getId(),
                                order.getProduct(),
                                order.getAmount(),
                                order.getUser().getId()
                        ))
                        .toList()
        );
    }
}