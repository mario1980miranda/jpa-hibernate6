package com.code.truck.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_client")
public class Client {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private GenderClient gender;
}
