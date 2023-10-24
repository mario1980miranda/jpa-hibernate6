package com.code.truck.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_client")
public class Client {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Transient
    private String firstName;

    @Enumerated(EnumType.STRING)
    private GenderClient gender;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @PostLoad
    public void recoverFirstName() {
        if (name != null && !name.isEmpty()) {
            int index = name.indexOf(" ");
            if (index > -1) {
                firstName = name.substring(0, index);
            }
        }
    }
}
