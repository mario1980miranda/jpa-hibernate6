package com.code.truck.ecommerce.basic_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ElementCollectionTests extends EntityManagerBaseTests {

    @Test
    public void addTagsToProduct() {

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);

        product.setTags(Arrays.asList("ebook", "digital-book"));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, product.getId());

        Assertions.assertFalse(productToAssert.getTags().isEmpty());
    }
}
