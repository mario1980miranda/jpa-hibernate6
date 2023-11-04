package com.code.truck.ecommerce.advanced_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.*;
import com.code.truck.ecommerce.model.enums.OrderStatus;
import com.code.truck.ecommerce.model.enums.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class InheritanceTests extends EntityManagerBaseTests {

    @Test
    public void saveCardPaymentForNewOrder() {

        Client client = entityManager.find(Client.class, 1);

        entityManager.getTransaction().begin();

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING);
        order.setTotal(new BigDecimal(500));
        order.setClient(client);

        CardPayment payment = new CardPayment();
        payment.setOrder(order);
        payment.setCardNumber("123");
        payment.setStatus(PaymentStatus.PROCESSING);

        entityManager.persist(payment);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Payment paymentToAssert = entityManager.find(Payment.class, payment.getId());
        Assertions.assertNotNull(paymentToAssert);

    }

    @Test
    public void saveSlipPaymentForNewOrder() {

        Client client = entityManager.find(Client.class, 1);

        entityManager.getTransaction().begin();

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING);
        order.setTotal(new BigDecimal(500));
        order.setClient(client);

        BankSlipPayment payment = new BankSlipPayment();
        payment.setOrder(order);
        payment.setBarCode("321");
        payment.setStatus(PaymentStatus.PROCESSING);

        entityManager.persist(payment);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, order.getId());
        Assertions.assertNotNull(orderToAssert.getPayment());
        Assertions.assertEquals("321", ((BankSlipPayment)orderToAssert.getPayment()).getBarCode());

    }
}
