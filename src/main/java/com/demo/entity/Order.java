package com.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Order() {
    }

    public Order(String product, BigDecimal amount, Users user) {
        this.product = product;
        this.amount = amount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id=id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}