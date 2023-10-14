package com.code.truck.ecommerce.model;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private BigDecimal productPrice;
    private Integer quantity;
}
