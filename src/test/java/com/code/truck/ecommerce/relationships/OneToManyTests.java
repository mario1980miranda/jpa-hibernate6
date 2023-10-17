package com.code.truck.ecommerce.relationships;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OneToManyTests extends EntityManagerBaseTests {
    @Test
    public void verifyClientToOrdersRelationship() {
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

        Client clientToAssert = entityManager.find(Client.class, client.getId());
        Assertions.assertFalse(clientToAssert.getOrders().isEmpty());
    }
}
