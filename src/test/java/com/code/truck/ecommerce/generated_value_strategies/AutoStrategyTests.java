package com.code.truck.ecommerce.generated_value_strategies;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoStrategyTests extends EntityManagerBaseTests {
    @Test
    public void testAutoStrategy() {

        Category category = new Category();
        category.setName("Category with AUTO strategy generated value.");

        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Category categoryToAssert = entityManager.find(Category.class, category.getId());
        Assertions.assertNotNull(categoryToAssert);
    }

    @Test
    public void testSequenceStrategy() {

        Category category = new Category();
        category.setName("Category with SEQUENCE strategy generated value.");

        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Category categoryToAssert = entityManager.find(Category.class, category.getId());
        Assertions.assertNotNull(categoryToAssert);
    }
}
