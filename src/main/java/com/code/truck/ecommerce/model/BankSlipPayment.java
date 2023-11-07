package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("bank_slip")
public class BankSlipPayment extends Payment {

    @Column(name = "bar_code", length = 100)
    private String barCode;

}
