package com.code.truck.ecommerce.model;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BankSlipPayment {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private Integer orderId;
    private PaymentStatus status;
    private String barCode;
}
