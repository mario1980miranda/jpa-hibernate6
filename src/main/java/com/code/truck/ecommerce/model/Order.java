package com.code.truck.ecommerce.model;

import com.code.truck.ecommerce.listeners.InvoiceListener;
import com.code.truck.ecommerce.model.base.BaseEntityInteger;
import com.code.truck.ecommerce.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EntityListeners({ InvoiceListener.class })
@Entity
@Table(name = "tb_order")
public class Order extends BaseEntityInteger {

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "last_update_date", insertable = false)
    private LocalDateTime lastUpdateDate;

    @Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private AddressDeliver address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToOne(mappedBy = "order")
    private Invoice invoice;

    public boolean isPaid() {
        return OrderStatus.PAID.equals(status);
    }

    public void calculateTotal() {
        System.out.println(">>>> calculateTotal <<<< ");
        if (orderItems != null) {
            total = this.orderItems.stream()
                    .map(
                            i -> i.getProductPrice()
                                    .multiply(BigDecimal.valueOf(i.getQuantity()))
                    )
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println("Total " + total);
        }
    }

    @PrePersist
    public void prePersist() {
        System.out.println("Before insert");
        this.createDate = LocalDateTime.now();
        calculateTotal();
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("Before update");
        this.lastUpdateDate = LocalDateTime.now();
        calculateTotal();
    }

    @PostPersist
    public void postPersist() {
        System.out.println("After insert");
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("After update");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("Before delete command");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("After remove");
    }

    @PostLoad
    public void postLoad() {
        System.out.println("After load from database");
    }
}
