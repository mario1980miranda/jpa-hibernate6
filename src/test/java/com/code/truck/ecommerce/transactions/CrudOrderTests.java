package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.AddressDeliver;
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.model.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CrudOrderTests extends EntityManagerBaseTests {
    @Test
    public void createOrder() {
        AddressDeliver address = new AddressDeliver();
        address.setCity("Québec");
        address.setRue("201-770 Jacques-Berthiaume");
        address.setProvince("Québec");
        address.setPostalCode("G1V 0E7");

        Order order = new Order();
        order.setId(1);
        order.setCreateDate(LocalDateTime.now());
        order.setTotal(new BigDecimal(5000));
        order.setStatus(OrderStatus.WAITING);
        order.setAddress(address);

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, 1);
        Assertions.assertNotNull(orderToAssert);
        Assertions.assertNotNull(orderToAssert.getAddress());
    }
}
