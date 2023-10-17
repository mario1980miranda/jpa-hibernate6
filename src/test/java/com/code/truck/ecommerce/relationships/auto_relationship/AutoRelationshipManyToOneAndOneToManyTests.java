package com.code.truck.ecommerce.relationships.auto_relationship;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoRelationshipManyToOneAndOneToManyTests extends EntityManagerBaseTests {

    @Test
    public void verifyAutoRelManyToOneAndOneToMany() {

        Category parentCategory = new Category();
        parentCategory.setName("Category Parent");

        Category childCategory = new Category();
        childCategory.setName("Category Child");
        childCategory.setParentCategory(parentCategory);

        entityManager.getTransaction().begin();
        entityManager.persist(parentCategory);
        entityManager.persist(childCategory);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Category childCategoryToAssert = entityManager.find(Category.class, childCategory.getId());
        Assertions.assertNotNull(childCategoryToAssert.getParentCategory());

        Category parentCategoryToAssert = entityManager.find(Category.class, parentCategory.getId());
        Assertions.assertFalse(parentCategoryToAssert.getCategories().isEmpty());
    }
}
