package com.code.truck.ecommerce.concurrence;

import com.code.truck.ecommerce.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LockOptimistic {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void setUpBeforeAll() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    static void tearDownAfterAll() {
        entityManagerFactory.close();
    }

    private static void log(Object obj, Object... args) {
        System.out.println(
                String.format("[LOG" + System.currentTimeMillis() + "] " + obj, args));
    }

    private static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) { }
    }

    @Test
    public void optimisticLock() {

        Runnable runnable1 = () -> {
            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            log("Runnable 01 will read Product 1");
            Product product = entityManager1.find(Product.class, 1);

            log("Runnable 01 will wait for 3 seconds");
            wait(3);

            log("Runnable 01 will modify product's description");
            product.setDescription("Awesome description");

            log("Runnable 01 will save description");
            entityManager1.getTransaction().commit();
            entityManager1.clear();
        };

        Runnable runnable2 = () -> {
            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            log("Runnable 02 will read Product 1");
            Product product = entityManager2.find(Product.class, 1);

            log("Runnable 02 will wait for 1 seconds");
            wait(1);

            log("Runnable 02 will modify product's description");
            product.setDescription("Detailed description");

            log("Runnable 02 will save description");
            entityManager2.getTransaction().commit();
            entityManager2.clear();
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        Product product = entityManager3.find(Product.class, 1);
        entityManager3.close();

        Assertions.assertEquals("Awesome description", product.getDescription());

        log("test class is finished");
    }
}
