package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class AddressDeliver {

    @Column(name = "postal_code", length = 6)
    private String postalCode;

    @Column(length = 100)
    private String rue;

    @Column(length = 50)
    private String complement;

    @Column(length = 2)
    private String province;

    @Column(length = 50)
    private String city;
}
