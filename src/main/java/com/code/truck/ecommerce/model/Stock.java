package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_stock")
public class Stock extends BaseEntityInteger {

    @Column(nullable = false)
    private Integer quantity;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
}
