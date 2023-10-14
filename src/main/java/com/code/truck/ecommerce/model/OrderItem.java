package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_order_item")
public class OrderItem {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_price")
    private BigDecimal productPrice;
    private Integer quantity;
}
