package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "tb_product",
        uniqueConstraints = {@UniqueConstraint(name = "unq_product_name", columnNames = {"name"})},
        indexes = {@Index(name = "idx_product_name", columnList = "name")}
)
public class Product extends BaseEntityInteger {

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "last_update_date", insertable = false)
    private LocalDateTime lasUpdateDate;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @OneToOne(mappedBy = "product")
    private Stock stock;

    @ElementCollection
    @CollectionTable(name = "tb_product_tag", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "tb_product_characteristic", joinColumns = @JoinColumn(name = "product_id"))
    private List<Attributes> attributes;

    // TODO : add product photo
}
