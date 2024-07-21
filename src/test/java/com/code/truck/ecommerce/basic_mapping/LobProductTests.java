package com.code.truck.ecommerce.basic_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class LobProductTests extends EntityManagerBaseTests {

    @Test
    public void loadProductPhotoToDatabase() {

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);
        product.setPhoto(loadKindlePhoto());

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, product.getId());
        Assertions.assertNotNull(productToAssert.getPhoto());
        Assertions.assertTrue(productToAssert.getPhoto().length > 0);
    }

    @Test
    public void insertProductPhotoToDatabase() {

        entityManager.getTransaction().begin();

        Product product = new Product();
        product.setName("Nintendo Switch Rev 2");
        product.setPrice(new BigDecimal("499.99"));
        product.setPhoto(loadNintendoSwitchPhoto());

        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, product.getId());
        Assertions.assertNotNull(productToAssert.getPhoto());
        Assertions.assertTrue(productToAssert.getPhoto().length > 0);
    }

    private byte[] loadKindlePhoto() {
        return loadFile("/kindle.jpg");
    }

    private byte[] loadNintendoSwitchPhoto() {
        return loadFile("/NintendoSwitch.jpg");
    }

    private byte[] loadFile(String name) {
        try {
            return Objects.requireNonNull(LobProductTests.class.getResourceAsStream(name)).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
