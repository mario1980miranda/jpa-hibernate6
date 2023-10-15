package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AddressDeliver {
    @Column(name = "postal_code")
    private String postalCode;
    private String rue;
    private String complement;
    private String province;
    private String city;
}
