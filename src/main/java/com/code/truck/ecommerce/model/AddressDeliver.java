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
    @Column(name = "postal_code")
    private String postalCode;
    private String rue;
    private String complement;
    private String province;
    private String city;
}
