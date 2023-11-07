package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_invoice")
public class Invoice extends BaseEntityInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @Lob
    @Column(nullable = false)
    @JdbcTypeCode(Types.LONGVARBINARY)
    private byte[] xml;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;
}
