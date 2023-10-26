package com.code.truck.ecommerce.basic_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Attributes;
import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void addCharacteristicsToProduct() {

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);

        product.setAttributes(Arrays.asList(new Attributes("Display", "AMOLED"), new Attributes("Screen Size","2560X1440"), new Attributes("Color", "White")));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, product.getId());

        Assertions.assertFalse(productToAssert.getAttributes().isEmpty());

    }
}
