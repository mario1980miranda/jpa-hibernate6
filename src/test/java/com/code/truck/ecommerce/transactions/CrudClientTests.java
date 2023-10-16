package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CrudClientTests extends EntityManagerBaseTests {

    // CREATE
    @Test
    public void createClient() {

        final String NAME = "Ozzy Osbourne";
        Client client = new Client();
        //client.setId(4); commented because of the primary key auto increment strategy
        client.setName(NAME);

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.clear();

        var clientToAssert = entityManager.find(Client.class, 4);
        Assertions.assertEquals(NAME, clientToAssert.getName());

    }

    // READ
    @Test
    public void readClient() {

        Client client = entityManager.find(Client.class, 3);

        Assertions.assertEquals("Gatusca & Nino", client.getName());
    }

    // UPDATE
    @Test
    public void updateClient() {

        //Client client = entityManager.find(Client.class, 2); // it is OK too
        Client client = entityManager.getReference(Client.class, 2);

        entityManager.getTransaction().begin();
        client.setName("Cristina Chan");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Assertions.assertEquals("Cristina Chan", client.getName());
    }

    // DELETE
    @Test
    public void deleteClient() {
        Client client = entityManager.find(Client.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Client clientToAssert = entityManager.find(Client.class, 1);

        Assertions.assertNull(clientToAssert);
    }
}
