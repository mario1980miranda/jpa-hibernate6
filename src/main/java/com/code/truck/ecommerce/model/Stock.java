package com.code.truck.ecommerce.model;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private Integer productId;
    private Integer quantity;
}
