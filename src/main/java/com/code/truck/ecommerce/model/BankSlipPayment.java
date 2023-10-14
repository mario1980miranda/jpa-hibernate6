package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_bank_slip_payment")
public class BankSlipPayment {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    private PaymentStatus status;
    @Column(name = "bar_code")
    private String barCode;
}
