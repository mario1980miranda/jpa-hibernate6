package com.code.truck.ecommerce.model;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private LocalDateTime createDate;
    private LocalDateTime conclusionDate;
    private Integer invoiceId;
    private BigDecimal total;
    private OrderStatus status;
}
