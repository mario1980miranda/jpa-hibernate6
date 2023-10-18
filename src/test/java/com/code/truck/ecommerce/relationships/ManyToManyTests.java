package com.code.truck.ecommerce.relationships;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Category;
import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class ManyToManyTests extends EntityManagerBaseTests {

    @Test
    public void verifyManyToManyProductToCategory() {

        Product product = entityManager.find(Product.class, 1);
        Category category = entityManager.find(Category.class, 1);


        entityManager.getTransaction().begin();
        //category.setProducts(Collections.singletonList(product)); // non-owning will not persist
        product.setCategories(Collections.singletonList(category)); // owner set will persist
        entityManager.getTransaction().commit();
        entityManager.clear();

        Category categoryToAssert = entityManager.find(Category.class, category.getId());
        Assertions.assertFalse(categoryToAssert.getProducts().isEmpty());
    }
}
