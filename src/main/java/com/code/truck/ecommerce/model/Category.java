package com.code.truck.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_category")
public class Category {
    // @formatter:off
    @EqualsAndHashCode.Include
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    //@SequenceGenerator(name = "my_seq", sequenceName = "my_tb_category_seq")
    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "table_strategy")
    /*@TableGenerator(name = "table_strategy",
                    table = "my_table_sequences",
                    pkColumnName = "sequence_name",
                    pkColumnValue = "category_sequence",
                    valueColumnName = "next_value",
                    initialValue = 0,
                    allocationSize = 10)*/ // default is 50
    // @formatter:on
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> categories;
}
