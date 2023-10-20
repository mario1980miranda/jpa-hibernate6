# Mapping Composite Keys

## @IdClass

```mermaid
---
title : Composite Key
---
erDiagram
    TB_ORDER_ITEM }|--|{ TB_ORDER : contains
    TB_ORDER_ITEM }|--|{ TB_PRODUCT : contains
    TB_ORDER_ITEM {
        integer order_id FK
        integer product_id FK
        bigint product_price
        integer quantity
    }
    TB_ORDER {
        integer id PK
        bigint total
        varchar status
    }
    TB_PRODUCT {
        integer id PK
        varchar name
        bigint price
    }
```
* Create a class to comport the composite ids :

```java
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {

    @EqualsAndHashCode.Include
    private Integer orderId;

    @EqualsAndHashCode.Include
    private Integer productId;
}
```

> In hibernate 6 the "implements Serializable" is not necessary anymore.

* Replace the @Id/@GeneratedValue with columns that represent the composite keys involved
(in this case: order_id and product_id).

```java
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@IdClass(OrderItemId.class) // C
@Table(name = "tb_order_item")
public class OrderItem {
    @EqualsAndHashCode.Include
    @Id // A
    @Column(name = "order_id")
    private Integer orderId;

    @EqualsAndHashCode.Include
    @Id // A
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", insertable = false, updatable = false) // B
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", insertable = false, updatable = false) // B
    private Product product;
}
```

> Notes :
>
> A. @Id annotation **MUST** be present for both attributes with the same name mapped on the objects
> 
> B. The objects that represent Order and Product **MUST** be transient, in this case it will work with the properties 
> insertable = false, updatable = false
>
> C. The class **MUST** be annotated with @IdClass(PK.class)

```java
    @Test
    public void verifyCompositeKeysOrderItemOrderIdAndOrderItemProductId() {

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);
        Client client = entityManager.find(Client.class, 1);

        Order order = new Order();
        order.setTotal(BigDecimal.TEN);
        order.setStatus(OrderStatus.WAITING);
        order.setCreateDate(LocalDateTime.now());
        order.setClient(client);

        entityManager.persist(order); // all entities involved must be persisted...
        entityManager.flush();

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId()); // ... in order to be used in the relationship
        orderItem.setProductId(product.getId());
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setProductPrice(product.getPrice());

        entityManager.persist(orderItem);

        entityManager.getTransaction().commit();
        entityManager.clear();

        OrderItem orderItemToAssert = entityManager.find(
                OrderItem.class, new OrderItemId(1,1)); // we need to use
                                                        // the PK class to find the entity
        Assertions.assertNotNull(orderItemToAssert.getOrder());
        Assertions.assertNotNull(orderItemToAssert.getProduct());
    }
```