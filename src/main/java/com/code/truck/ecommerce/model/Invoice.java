package com.code.truck.ecommerce.model;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private Integer orderId;
    private String xml;
    private Date issueDate;
}
