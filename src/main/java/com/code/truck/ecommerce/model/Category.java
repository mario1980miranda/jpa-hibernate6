package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_category", uniqueConstraints = {@UniqueConstraint(name = "unq_category_name", columnNames = {"name"})})
public class Category extends BaseEntityInteger {

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> categories;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
