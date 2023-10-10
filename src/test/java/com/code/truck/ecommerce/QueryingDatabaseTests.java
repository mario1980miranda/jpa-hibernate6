package com.code.truck.ecommerce;

import com.code.truck.ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class QueryingDatabaseTests {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

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

    @Test
    public void retrieveDataWithFind() {

        Produto produto = entityManager.find(Produto.class, 1);

        System.out.println(">>>>>> With find we will see the SELECT command within the Database before this line.");

        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void retrieveDataWithGetReference() {

        Produto produto = entityManager.getReference(Produto.class, 1);

        System.out.println(">>>>>> With getReference we will see the SELECT command after this line. After the call in the de code \"produto.getNome()\"");

        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void updateReference() {

        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Microphone");

        entityManager.refresh(produto); // reset entity in the database

        Assertions.assertEquals("Kindle", produto.getNome());
    }
}
