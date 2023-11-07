package com.code.truck.ecommerce.advanced_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Client;
import com.code.truck.ecommerce.model.enums.GenderClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SecondaryTableTests extends EntityManagerBaseTests {

    @Test
    public void saveClientWithDetails() {

        entityManager.getTransaction().begin();

        Client client = new Client();
        client.setName("Client Details");
        client.setCpf("98765431209");
        client.setGender(GenderClient.FEMALE);
        client.setBirthDate(LocalDate.of(1980, 11, 15));

        entityManager.persist(client);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Client clientToAssert = entityManager.find(Client.class, client.getId());
        Assertions.assertNotNull(clientToAssert.getGender());
        Assertions.assertEquals(LocalDate.of(1980, 11, 15), clientToAssert.getBirthDate());
    }
}
