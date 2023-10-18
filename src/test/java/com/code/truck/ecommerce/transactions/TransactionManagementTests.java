package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.model.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionManagementTests extends EntityManagerBaseTests {

    @Test
    public void verifyRollback() {

        Assertions.assertThrows(Exception.class, () -> methodToTest());

    }

    private void methodToTest() {

        try {

            entityManager.getTransaction().begin();
            businessRule();
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();
            throw e;
        }

    }

    private void businessRule() {

        Order order = entityManager.find(Order.class, 1);

        order.setStatus(OrderStatus.PAID);

        if (order.getCardPayment() == null)
            throw new RuntimeException("Order is not paid.");

    }
}
