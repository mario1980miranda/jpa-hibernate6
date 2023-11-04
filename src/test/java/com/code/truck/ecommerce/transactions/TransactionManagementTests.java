package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.model.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionManagementTests extends EntityManagerBaseTests {

    @Test
    public void exceptionWithFlush() {

        Assertions.assertThrows(Exception.class, () -> {

            try {
                entityManager.getTransaction().begin();

                Order order = entityManager.find(Order.class, 2);
                order.setStatus(OrderStatus.PAID);

                if (order.getPayment() == null) {
                    throw new RuntimeException("Order not paid");
                }


                entityManager.getTransaction().commit();
            } catch (Exception e) {

                entityManager.getTransaction().rollback();
                throw e;
            }
        });
    }

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

        Order order = entityManager.find(Order.class, 2);

        order.setStatus(OrderStatus.PAID);

        if (order.getPayment() == null)
            throw new RuntimeException("Order is not paid.");

    }
}
