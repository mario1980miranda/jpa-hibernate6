package com.code.truck.ecommerce.basic_mapping;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransientPropertyTests extends EntityManagerBaseTests {

    @Test
    public void recoverFirstNameTransientPropertyShouldReturn() {

        Client client = entityManager.find(Client.class, 1);

        Assertions.assertEquals("Mario", client.getFirstName());
    }
}
