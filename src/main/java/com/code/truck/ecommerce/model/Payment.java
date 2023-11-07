package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import com.code.truck.ecommerce.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_payment")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment extends BaseEntityInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
