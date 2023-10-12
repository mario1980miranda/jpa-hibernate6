package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperationsWithTransactionsTests extends EntityManagerBaseTests {

    @Test
    public void removerProduto() {

        // this will cause :
        // java.lang.IllegalArgumentException: Removing a detached instance com.code.truck.ecommerce.model.Produto#3
        //Produto produto = new Produto();
        //produto.setId(3);
        // EntityManager must know the object in order to interact with it
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();

        entityManager.remove(produto);

        entityManager.getTransaction().commit();

        Produto produtoVerifier = entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoVerifier);
    }

    @Test
    public void insertProduct() {

        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Camera Canon");
        produto.setDescricao("A melhor camera do mercado");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        entityManager.persist(produto);

        entityManager.getTransaction().commit();

        System.out.println(">>>> Here we don`t see a SELECT because of the entity manager is using a cache in memory");
        Produto productToAssert = entityManager.find(Produto.class, 2);

        Assertions.assertNotNull(productToAssert);

        System.out.println(">>>> Now the method clear() of the entity manager will reset the cache and force a " +
                "SELECT in the database");

        entityManager.clear();

        Produto productToAssertAfterClearCache = entityManager.find(Produto.class, 2);

        Assertions.assertNotNull(productToAssertAfterClearCache);
    }

}
