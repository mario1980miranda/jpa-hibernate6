package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallbackEventsTests extends EntityManagerBaseTests {

    @Test
    public void invokePrePersistAndPreUpdateCallbackMethods() {

        Client client = entityManager.find(Client.class, 1);
        Product product1 = entityManager.find(Product.class, 1);
        Product product2 = entityManager.find(Product.class, 2);

        entityManager.getTransaction().begin();

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING);
        order.setClient(client);

        Assertions.assertNull(order.getCreateDate());
        Assertions.assertNull(order.getLastUpdateDate());

        entityManager.persist(order);
        entityManager.flush();

        OrderItem item1 = new OrderItem();
//        item1.setOrderId(order.getId()); // @IdClass
//        item1.setProductId(product1.getId()); // @IdClass
        item1.setId(new OrderItemId());
        item1.setOrder(order);
        item1.setProduct(product1);
        item1.setProductPrice(new BigDecimal("10.00"));
        item1.setQuantity(2);

        OrderItem item2 = new OrderItem();
//        item2.setOrderId(order.getId()); // @IdClass
//        item2.setProductId(product2.getId()); // @IdClass
        item2.setId(new OrderItemId());
        item2.setOrder(order);
        item2.setProduct(product2);
        item2.setProductPrice(new BigDecimal("3.00"));
        item2.setQuantity(5);

        order.setOrderItems(Arrays.asList(item1, item2));

        System.out.println("Items is empty: " + order.getOrderItems().isEmpty());

        entityManager.persist(item1);
        entityManager.persist(item2);

        entityManager.flush();

        order.setStatus(OrderStatus.PAID);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, order.getId());

        Assertions.assertNotNull(orderToAssert.getCreateDate());
        Assertions.assertNotNull(orderToAssert.getLastUpdateDate());
    }
}
