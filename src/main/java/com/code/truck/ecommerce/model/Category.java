package com.code.truck.ecommerce.model;

import jakarta.persistence.*;
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
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name = "my_seq", sequenceName = "my_tb_category_seq")
    private Integer id;
    private String name;
    @Column(name = "parent_category_id")
    private Integer parentCategoryId;
}
