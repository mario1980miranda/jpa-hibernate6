package com.code.truck.ecommerce;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class EntityManagerBaseTests {
    protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    @BeforeAll
    public static void setUpBeforeAllTests() throws Exception {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    public static void tearDownAfterAllTests() {
        entityManagerFactory.close();
    }

    @BeforeEach
    public void setUpForEachTest() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void afterEachTest() {
        entityManager.close();
    }
}
