package com.code.truck.ecommerce.basic_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Client;
import com.code.truck.ecommerce.model.GenderClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MappingEnumsTests extends EntityManagerBaseTests {

    @Test
    public void testEnums() {

        Client client = new Client();
        //client.setId(5); commented because of the primary key auto increment strategy
        client.setName("James Hatfield");
        client.setGender(GenderClient.MALE);

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Client clientToAssert = entityManager.find(Client.class, client.getId());
        Assertions.assertNotNull(clientToAssert);
    }
}
