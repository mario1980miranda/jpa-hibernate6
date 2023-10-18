package com.code.truck.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_invoice")
public class Invoice {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String xml;
    @Column(name = "issue_date")
    private Date issueDate;

    @OneToOne
    @JoinTable(name = "tb_invoice_order",
            joinColumns = @JoinColumn(name = "invoice_id", unique = true),
            inverseJoinColumns = @JoinColumn(name = "order_id", unique = true))
    private Order order;
}
