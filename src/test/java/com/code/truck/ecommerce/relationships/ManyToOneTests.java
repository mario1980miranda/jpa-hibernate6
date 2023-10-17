package com.code.truck.ecommerce.relationships;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ManyToOneTests extends EntityManagerBaseTests {
    @Test
    public void verifyOrderToClientRelationship() {
        Client client = entityManager.find(Client.class, 1);

        AddressDeliver address = new AddressDeliver();
        address.setPostalCode("A1A 1A1");
        address.setRue("6571, Blaze Pascale");
        address.setCity("Québec");
        address.setProvince("Québec");

        Order order = new Order();
        order.setCreateDate(LocalDateTime.now());
        order.setStatus(OrderStatus.WAITING);

        order.setAddress(address);
        order.setClient(client);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, order.getId());
        System.out.println(orderToAssert);
        Assertions.assertNotNull(orderToAssert.getClient());
    }

    @Test
    public void verifyOrderItemToOrderAndOrderItemToProduct() {
        Product product = entityManager.find(Product.class, 1);
        Client client = entityManager.find(Client.class, 1);

        Order order = new Order();
        order.setTotal(BigDecimal.TEN);
        order.setStatus(OrderStatus.WAITING);
        order.setCreateDate(LocalDateTime.now());
        order.setClient(client);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setProductPrice(product.getPrice());

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.persist(orderItem);
        entityManager.getTransaction().commit();
        entityManager.clear();

        OrderItem orderItemToAssert = entityManager.find(OrderItem.class, orderItem.getId());
        System.out.println(orderItemToAssert);
        Assertions.assertNotNull(orderItemToAssert.getOrder());
        Assertions.assertNotNull(orderItemToAssert.getProduct());
    }
}
