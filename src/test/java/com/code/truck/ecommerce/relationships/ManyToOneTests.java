package com.code.truck.ecommerce.relationships;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.AddressDeliver;
import com.code.truck.ecommerce.model.Client;
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.model.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
