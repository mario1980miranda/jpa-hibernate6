package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_stock")
public class Stock extends BaseEntityInteger {

    private Integer quantity;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
}
