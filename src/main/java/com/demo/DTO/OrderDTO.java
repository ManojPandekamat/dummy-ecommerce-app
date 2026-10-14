package com.demo.DTO;



import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private Long id;
    private String product;
    private BigDecimal amount;
    private Long userId;

}