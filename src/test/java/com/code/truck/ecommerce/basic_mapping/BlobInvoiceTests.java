package com.code.truck.ecommerce.basic_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Invoice;
import com.code.truck.ecommerce.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

public class BlobInvoiceTests extends EntityManagerBaseTests {

    @Test
    public void saveLobXmlIntoInvoiceShouldPersist() {

        Order order = entityManager.find(Order.class, 1);

        entityManager.getTransaction().begin();

        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setIssueDate(new Date());
        invoice.setXml(loadInvoiceXmlFile());

        entityManager.persist(invoice);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Invoice invoiceToAssert = entityManager.find(Invoice.class, invoice.getId());
        Assertions.assertNotNull(invoiceToAssert.getXml());
        Assertions.assertTrue(invoiceToAssert.getXml().length > 0);

        // just to verify integrity of file
        /*
        try (OutputStream out = new FileOutputStream(
                Files.createFile(
                        Paths.get(System.getProperty("user.home") + "/test-lob.xml")).toFile())) {
            out.write(invoiceToAssert.getXml());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */
    }

    private static byte[] loadInvoiceXmlFile() {

        try {

            return Objects.requireNonNull(BlobInvoiceTests.class.getResourceAsStream("/invoice.xml")).readAllBytes();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
