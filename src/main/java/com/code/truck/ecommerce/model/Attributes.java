package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Attributes {

    @Column(name = "characteristic_name")
    private String key;

    @Column(name = "characteristic_value")
    private String value;
}
