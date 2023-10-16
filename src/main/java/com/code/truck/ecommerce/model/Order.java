package com.code.truck.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_order")
public class Order {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;
    @Column(name = "invoice_id")
    private Integer invoiceId;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Embedded
    private AddressDeliver address;
}
