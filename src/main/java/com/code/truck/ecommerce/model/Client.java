package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import com.code.truck.ecommerce.model.enums.GenderClient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "tb_client")
@SecondaryTable(name = "tb_client_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "client_id"))
public class Client extends BaseEntityInteger {

    private String name;

    @ElementCollection
    @CollectionTable(
            name = "tb_client_contact_type",
            joinColumns = @JoinColumn(name = "client_id"))
    @MapKeyColumn(name = "type")
    @Column(name = "description")
    private Map<String, String> contactTypes;

    @Transient
    private String firstName;

    @Column(table = "tb_client_details")
    @Enumerated(EnumType.STRING)
    private GenderClient gender;

    @Column(name = "birth_date", table = "tb_client_details")
    private LocalDate birthDate;

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
