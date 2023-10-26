package com.code.truck.ecommerce.basic_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Attributes;
import com.code.truck.ecommerce.model.Client;
import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementCollectionTests extends EntityManagerBaseTests {

    @Test
    public void addTagsToProduct() {

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);

        product.setTags(List.of("ebook", "digital-book"));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, product.getId());

        Assertions.assertFalse(productToAssert.getTags().isEmpty());
    }

    @Test
    public void addCharacteristicsToProduct() {

        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);

        product.setAttributes(List.of(new Attributes("Display", "AMOLED"), new Attributes("Screen Size","2560X1440"), new Attributes("Color", "White")));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productToAssert = entityManager.find(Product.class, product.getId());

        Assertions.assertFalse(productToAssert.getAttributes().isEmpty());

    }

    @Test
    public void addContactTypeToClient() {

        entityManager.getTransaction().begin();

        Client client = entityManager.find(Client.class, 1);

        Map<String,String> contacts = new HashMap<>();
        contacts.put("email", "contato@email.com");
        contacts.put("home phone", "(581)123-4567");
        contacts.put("téléphone", "(123)456-7890");

        client.setContactTypes(contacts);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Client clientToAssert = entityManager.find(Client.class, client.getId());

        Assertions.assertEquals("(123)456-7890", clientToAssert.getContactTypes().get("téléphone"));

    }
}
