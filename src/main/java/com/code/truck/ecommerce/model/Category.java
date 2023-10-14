package com.code.truck.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_category")
public class Category {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private String name;
    @Column(name = "parent_category_id")
    private Integer parentCategoryId;
}
