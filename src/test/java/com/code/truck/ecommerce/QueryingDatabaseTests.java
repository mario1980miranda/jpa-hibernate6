package com.code.truck.ecommerce;

import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryingDatabaseTests extends EntityManagerBaseTests {
    @Test
    public void retrieveDataWithFind() {

        Product product = entityManager.find(Product.class, 1);

        System.out.println(">>>>>> With find we will see the SELECT command within the Database before this line.");

        Assertions.assertNotNull(product);
        Assertions.assertEquals("Kindle", product.getName());
    }

    @Test
    public void retrieveDataWithGetReference() {

        Product product = entityManager.getReference(Product.class, 1);

        System.out.println(">>>>>> With getReference we will see the SELECT command after this line. After the call in the de code \"produto.getNome()\"");

        Assertions.assertNotNull(product);
        Assertions.assertEquals("Kindle", product.getName());
    }

    @Test
    public void updateReference() {

        Product product = entityManager.find(Product.class, 1);

        product.setName("Microphone");

        entityManager.refresh(product); // reset entity in the database

        Assertions.assertEquals("Kindle", product.getName());
    }
}
