package com.code.truck.ecommerce.advanced_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.*;
import com.code.truck.ecommerce.model.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompositeKeysTests extends EntityManagerBaseTests {

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

//        entityManager.persist(order);
//        entityManager.flush(); // don't to persist and flush order since we used @MapsId in OrderItem

        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(order.getId()); // @IdClass
//        orderItem.setProductId(product.getId()); // @IdClass
//        orderItem.setId(new OrderItemId(order.getId(), product.getId())); // update=false,insert=false
        orderItem.setId(new OrderItemId());
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setProductPrice(product.getPrice());

        entityManager.persist(orderItem);

        entityManager.getTransaction().commit();
        entityManager.clear();

        OrderItem orderItemToAssert = entityManager.find(OrderItem.class, new OrderItemId(order.getId(), product.getId()));
        Assertions.assertNotNull(orderItemToAssert.getOrder());
        Assertions.assertNotNull(orderItemToAssert.getProduct());
    }

    @Test
    public void findEntityWithCompositeKeysShouldReturnFromDatabase() {

        OrderItem orderItem = entityManager.find(OrderItem.class, new OrderItemId(1, 1));

        Assertions.assertNotNull(orderItem);
    }
}
