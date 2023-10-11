package com.code.truck.ecommerce;

import com.code.truck.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryingDatabaseTests extends EntityManagerBaseTests {
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
