package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    private String xml;
    @Column(name = "issue_date")
    private Date issueDate;
}
