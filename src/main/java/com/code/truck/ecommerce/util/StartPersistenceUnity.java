package com.code.truck.ecommerce.util;

import com.code.truck.ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StartPersistenceUnity {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // put your tests here
        //Produto p = entityManager.find(Produto.class, 1);
        //System.out.println(p.getId());

        entityManager.close();
        entityManagerFactory.close();

    }
}
