package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItemId {

    @EqualsAndHashCode.Include
    @Column(name = "order_id")
    private Integer orderId;

    @EqualsAndHashCode.Include
    @Column(name = "product_id")
    private Integer productId;
}
