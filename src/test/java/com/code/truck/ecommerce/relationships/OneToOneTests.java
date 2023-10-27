package com.code.truck.ecommerce.relationships;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.CardPayment;
import com.code.truck.ecommerce.model.Invoice;
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.model.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class OneToOneTests extends EntityManagerBaseTests {

    @Test
    public void verifyCardPaymentToOrder() {

        Order order = entityManager.find(Order.class, 1);

        CardPayment cardPayment = new CardPayment();
        cardPayment.setNumber("1234 5678 9012 3456");
        cardPayment.setStatus(PaymentStatus.PROCESSING);
        cardPayment.setOrder(order);

        entityManager.getTransaction().begin();
        entityManager.persist(cardPayment);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, order.getId());
        Assertions.assertNotNull(orderToAssert.getCardPayment());
    }

    @Test
    public void verifyInvoiceToOrder() {

        Order order = entityManager.find(Order.class, 1);

        Invoice invoice = new Invoice();
        invoice.setIssueDate(new Date());
        invoice.setOrder(order);

        entityManager.getTransaction().begin();
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Order orderToAssert = entityManager.find(Order.class, order.getId());
        Assertions.assertNotNull(orderToAssert.getInvoice());
    }
}
