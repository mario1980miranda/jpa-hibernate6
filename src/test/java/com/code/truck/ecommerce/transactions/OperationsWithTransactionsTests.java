package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperationsWithTransactionsTests extends EntityManagerBaseTests {

    @Test
    public void detachEntityFromEntityManager() {
        Product product = entityManager.find(Product.class, 9);

        entityManager.detach(product);

        entityManager.getTransaction().begin();
        product.setName("New IPad");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Product productToAssertAfterClearCache = entityManager.find(Product.class, 9);
        Assertions.assertEquals("IPad", productToAssertAfterClearCache.getName());
    }

    @Test
    public void updateEntityManegedByEntityManager() {
        Product product = entityManager.find(Product.class, 4);

        entityManager.getTransaction().begin();
        product.setName("Blu ray player");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Product productToAssertAfterClearCache = entityManager.find(Product.class, 4);
        Assertions.assertEquals("Blu ray player", productToAssertAfterClearCache.getName());
    }

    @Test
    public void updateEntityNotManagedByEntityManager() {

        Product product = new Product();
        product.setId(1);
        product.setName("Novo Kindle Paperwhite");
        // This will cause Description and Price to be null
        //produto.setDescription("Conheça o novo Kindle, agora com bla, bla bla");
        //produto.setPrice(BigDecimal.valueOf(499.90d));

        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Product productToAssertAfterClearCache = entityManager.find(Product.class, 1);

        Assertions.assertNotNull(productToAssertAfterClearCache);
        Assertions.assertEquals("Novo Kindle Paperwhite", productToAssertAfterClearCache.getName());
    }
    @Test
    public void removeEntity() {

        // this will cause :
        // java.lang.IllegalArgumentException: Removing a detached instance com.code.truck.ecommerce.model.Produto#3
        //Produto produto = new Produto();
        //produto.setId(3);
        // EntityManager must know the object in order to interact with it
        Product product = entityManager.find(Product.class, 3);

        entityManager.getTransaction().begin();

        entityManager.remove(product);

        entityManager.getTransaction().commit();

        Product productVerifier = entityManager.find(Product.class, 3);
        Assertions.assertNull(productVerifier);
    }

    @Test
    public void insertEntityWithMerge() {

        Product product = new Product();
        product.setId(5);
        product.setName("Microphone Rode Videmic");
        product.setDescription("Best quality of sound.");
        product.setPrice(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        entityManager.merge(product);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Product productToAssertAfterClearCache = entityManager.find(Product.class, 5);

        Assertions.assertNotNull(productToAssertAfterClearCache);
    }

    @Test
    public void showDifferenceBetweenPersistenceAndMerge() {

        System.out.println(">>> PERSIST <<<");
        Product productPersist = new Product();
        productPersist.setId(6);
        productPersist.setName("XBox");
        productPersist.setDescription("Microsoft's console");
        productPersist.setPrice(new BigDecimal(599));

        entityManager.getTransaction().begin();
        System.out.println(">>> Persist : After commit INSERT command will execute");
        entityManager.persist(productPersist);
        System.out.println(">>> Persist : Now the entity persisted is managed by JPA");
        System.out.println(">>> Persist : This will result in a UPDATE command by a simple SET");
        productPersist.setName("XBox series X");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Product productPersistToAssert = entityManager.find(Product.class, 6);
        Assertions.assertNotNull(productPersistToAssert);
        Assertions.assertEquals("XBox series X", productPersistToAssert.getName());

        System.out.println(">>> MERGE [just copy] <<<");

        Product productMergeJustCopy = new Product();
        productMergeJustCopy.setId(7);
        productMergeJustCopy.setName("PlayStation 4");
        productMergeJustCopy.setDescription("Sony's console");
        productMergeJustCopy.setPrice(new BigDecimal(399));

        entityManager.getTransaction().begin();
        System.out.println(">>> MERGE : INSERT command will execute");
        System.out.println(">>> MERGE : Just make a copy of the entity and transfers it to EM of JPA");
        entityManager.merge(productMergeJustCopy);
        System.out.println(">>> MERGE : productMergeJustCopy.setNome(\"PlayStation 4 PRO\") " +
                "will not take effect in the database");
        productMergeJustCopy.setName("PlayStation 4 PRO");
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println(">>> MERGE [Reassigned local variable] <<<");

        Product productMergeJustCopyToAssert = entityManager.find(Product.class, 7);
        Assertions.assertNotNull(productMergeJustCopyToAssert);
        Assertions.assertEquals("PlayStation 4", productMergeJustCopyToAssert.getName());

        Product productMerge = new Product();
        productMerge.setId(8);
        productMerge.setName("PSX");
        productMerge.setDescription("Sony's console");
        productMerge.setPrice(new BigDecimal(599));

        entityManager.getTransaction().begin();
        System.out.println(">>> MERGE : INSERT command will execute");
        System.out.println(">>> MERGE : We need to set the value of productMerge with the return of " + "\n" +
                "entityManager.merge(productMerge) operation" + "\n" +
                "if not, the entity is not managed, just a copy of it");
        productMerge = entityManager.merge(productMerge);
        System.out.println(">>> MERGE : Now the entity persisted is managed by JPA");
        System.out.println(">>> MERGE : This will result in a UPDATE command by a simple SET");
        productMerge.setName("PlayStation 5");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Product productMergeToAssert = entityManager.find(Product.class, 8);
        Assertions.assertNotNull(productMergeToAssert);
        Assertions.assertEquals("PlayStation 5", productMergeToAssert.getName());
    }

    @Test
    public void insertEntity() {

        Product product = new Product();
        product.setId(2);
        product.setName("Camera Canon");
        product.setDescription("A melhor camera do mercado");
        product.setPrice(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        entityManager.persist(product);

        entityManager.getTransaction().commit();

        System.out.println(">>>> Here we don`t see a SELECT because of the entity manager is using a cache in memory");
        Product productToAssert = entityManager.find(Product.class, 2);

        Assertions.assertNotNull(productToAssert);

        System.out.println(">>>> Now the method clear() of the entity manager will reset the cache and force a " +
                "SELECT in the database");

        entityManager.clear();

        Product productToAssertAfterClearCache = entityManager.find(Product.class, 2);

        Assertions.assertNotNull(productToAssertAfterClearCache);
    }

}
