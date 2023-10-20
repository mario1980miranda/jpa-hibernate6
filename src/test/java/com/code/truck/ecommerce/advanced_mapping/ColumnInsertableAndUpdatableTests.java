package com.code.truck.ecommerce.advanced_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ColumnInsertableAndUpdatableTests extends EntityManagerBaseTests {

    @Test
    public void updateColumnCreateDateShouldNotModifyData() {

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);

        product.setCreateDate(LocalDateTime.now());
        product.setLasUpdateDate(LocalDateTime.now());

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, 1);
        Assertions.assertNotEquals(
                product.getCreateDate().truncatedTo(ChronoUnit.SECONDS),
                productToAssert.getCreateDate().truncatedTo(ChronoUnit.SECONDS));
        Assertions.assertEquals(
                product.getLasUpdateDate().truncatedTo(ChronoUnit.SECONDS),
                productToAssert.getLasUpdateDate().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void settingValueToLastUpdateDateColumnDuringInsertShouldDoNothing() {

        Product product = new Product();

        product.setName("Product to test last update column");
        product.setDescription("During INSERT the column annotated with insertable=false should not take effect");
        product.setCreateDate(LocalDateTime.now());
        product.setLasUpdateDate(LocalDateTime.now());

        entityManager.getTransaction().begin();

        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, product.getId());
        Assertions.assertNotNull(productToAssert.getCreateDate());
        Assertions.assertEquals(
                product.getCreateDate().truncatedTo(ChronoUnit.SECONDS),
                productToAssert.getCreateDate().truncatedTo(ChronoUnit.SECONDS));
        Assertions.assertNull(productToAssert.getLasUpdateDate());
    }
}
