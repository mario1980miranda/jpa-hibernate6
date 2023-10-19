package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Client;
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.model.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CallbackEventsTests extends EntityManagerBaseTests {

    @Test
    public void invokePrePersistAndPreUpdateCallbackMethods() {

        Client client = entityManager.find(Client.class, 1);

        entityManager.getTransaction().begin();

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING);
        order.setClient(client);

        Assertions.assertNull(order.getCreateDate());
        Assertions.assertNull(order.getLastUpdateDate());

        entityManager.persist(order);

        entityManager.flush();

        order.setStatus(OrderStatus.PAID);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, order.getId());

        Assertions.assertNotNull(orderToAssert.getCreateDate());
        Assertions.assertNotNull(orderToAssert.getLastUpdateDate());

    }
}
