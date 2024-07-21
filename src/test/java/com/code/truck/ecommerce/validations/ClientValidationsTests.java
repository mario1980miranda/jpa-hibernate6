package com.code.truck.ecommerce.validations;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ClientValidationsTests extends EntityManagerBaseTests {

    @Test
    @Disabled
    public void testClientInsertWithoutCPF() {

        Client client = new Client();
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}
