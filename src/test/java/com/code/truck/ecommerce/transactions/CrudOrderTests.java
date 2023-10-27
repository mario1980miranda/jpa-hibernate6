package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.AddressDeliver;
import com.code.truck.ecommerce.model.Client;
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.model.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CrudOrderTests extends EntityManagerBaseTests {
    @Test
    public void createOrder() {

        Client client = entityManager.find(Client.class, 2);

        AddressDeliver address = new AddressDeliver();
        address.setCity("Québec");
        address.setRue("201-770 Chemin Ste-Foy");
        address.setProvince("Québec");
        address.setPostalCode("A1A 1A1");

        Order order = new Order();
        //order.setId(1); commented because of the primary key auto increment strategy
        order.setCreateDate(LocalDateTime.now());
        order.setTotal(new BigDecimal(5000));
        order.setStatus(OrderStatus.WAITING);
        order.setAddress(address);
        order.setClient(client);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, order.getId());
        Assertions.assertNotNull(orderToAssert);
        Assertions.assertNotNull(orderToAssert.getAddress());
    }
}
