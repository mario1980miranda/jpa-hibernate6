package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("card")
public class CardPayment extends Payment {

    @Column(name = "card_number")
    private String cardNumber;

}
