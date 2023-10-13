package com.code.truck.ecommerce.transactions;

import com.code.truck.ecommerce.EntityManagerBaseTests;
import com.code.truck.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperationsWithTransactionsTests extends EntityManagerBaseTests {

    @Test
    public void detachEntityFromEntityManager() {
        Produto produto = entityManager.find(Produto.class, 4);

        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("Blu ray player");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto productToAssertAfterClearCache = entityManager.find(Produto.class, 4);
        Assertions.assertEquals("DVD Player", productToAssertAfterClearCache.getNome());
    }

    @Test
    public void updateEntityManegedByEntityManager() {
        Produto produto = entityManager.find(Produto.class, 4);

        entityManager.getTransaction().begin();
        produto.setNome("Blu ray player");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto productToAssertAfterClearCache = entityManager.find(Produto.class, 4);
        Assertions.assertEquals("Blu ray player", productToAssertAfterClearCache.getNome());
    }

    @Test
    public void updateEntityNotManagedByEntityManager() {

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Novo Kindle Paperwhite");
        // This will cause Description and Price to be null
        //produto.setDescription("Conheça o novo Kindle, agora com bla, bla bla");
        //produto.setPrice(BigDecimal.valueOf(499.90d));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto productToAssertAfterClearCache = entityManager.find(Produto.class, 1);

        Assertions.assertNotNull(productToAssertAfterClearCache);
        Assertions.assertEquals("Novo Kindle Paperwhite", productToAssertAfterClearCache.getNome());
    }
    @Test
    public void removeEntity() {

        // this will cause :
        // java.lang.IllegalArgumentException: Removing a detached instance com.code.truck.ecommerce.model.Produto#3
        //Produto produto = new Produto();
        //produto.setId(3);
        // EntityManager must know the object in order to interact with it
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();

        entityManager.remove(produto);

        entityManager.getTransaction().commit();

        Produto produtoVerifier = entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoVerifier);
    }

    @Test
    public void insertEntityWithMerge() {

        Produto produto = new Produto();
        produto.setId(5);
        produto.setNome("Microphone Rode Videmic");
        produto.setDescricao("Best quality of sound.");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        entityManager.merge(produto);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto productToAssertAfterClearCache = entityManager.find(Produto.class, 5);

        Assertions.assertNotNull(productToAssertAfterClearCache);
    }

    @Test
    public void showDifferenceBetweenPersistenceAndMerge() {

        System.out.println(">>> PERSIST <<<");
        Produto productPersist = new Produto();
        productPersist.setId(6);
        productPersist.setNome("XBox");
        productPersist.setDescricao("Microsoft's console");
        productPersist.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        System.out.println(">>> Persist : After commit INSERT command will execute");
        entityManager.persist(productPersist);
        System.out.println(">>> Persist : Now the entity persisted is managed by JPA");
        System.out.println(">>> Persist : This will result in a UPDATE command by a simple SET");
        productPersist.setNome("XBox series X");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto productPersistToAssert = entityManager.find(Produto.class, 6);
        Assertions.assertNotNull(productPersistToAssert);
        Assertions.assertEquals("XBox series X", productPersistToAssert.getNome());

        System.out.println(">>> MERGE [just copy] <<<");

        Produto productMergeJustCopy = new Produto();
        productMergeJustCopy.setId(7);
        productMergeJustCopy.setNome("PlayStation 4");
        productMergeJustCopy.setDescricao("Sony's console");
        productMergeJustCopy.setPreco(new BigDecimal(399));

        entityManager.getTransaction().begin();
        System.out.println(">>> MERGE : INSERT command will execute");
        System.out.println(">>> MERGE : Just make a copy of the entity and transfers it to EM of JPA");
        entityManager.merge(productMergeJustCopy);
        System.out.println(">>> MERGE : productMergeJustCopy.setNome(\"PlayStation 4 PRO\") " +
                "will not take effect in the database");
        productMergeJustCopy.setNome("PlayStation 4 PRO");
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println(">>> MERGE [Reassigned local variable] <<<");

        Produto productMergeJustCopyToAssert = entityManager.find(Produto.class, 7);
        Assertions.assertNotNull(productMergeJustCopyToAssert);
        Assertions.assertEquals("PlayStation 4", productMergeJustCopyToAssert.getNome());

        Produto productMerge = new Produto();
        productMerge.setId(8);
        productMerge.setNome("PSX");
        productMerge.setDescricao("Sony's console");
        productMerge.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        System.out.println(">>> MERGE : INSERT command will execute");
        System.out.println(">>> MERGE : We need to set the value of productMerge with the return of " + "\n" +
                "entityManager.merge(productMerge) operation" + "\n" +
                "if not, the entity is not managed, just a copy of it");
        productMerge = entityManager.merge(productMerge);
        System.out.println(">>> MERGE : Now the entity persisted is managed by JPA");
        System.out.println(">>> MERGE : This will result in a UPDATE command by a simple SET");
        productMerge.setNome("PlayStation 5");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto productMergeToAssert = entityManager.find(Produto.class, 8);
        Assertions.assertNotNull(productMergeToAssert);
        Assertions.assertEquals("PlayStation 5", productMergeToAssert.getNome());
    }

    @Test
    public void insertEntity() {

        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Camera Canon");
        produto.setDescricao("A melhor camera do mercado");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();

        entityManager.persist(produto);

        entityManager.getTransaction().commit();

        System.out.println(">>>> Here we don`t see a SELECT because of the entity manager is using a cache in memory");
        Produto productToAssert = entityManager.find(Produto.class, 2);

        Assertions.assertNotNull(productToAssert);

        System.out.println(">>>> Now the method clear() of the entity manager will reset the cache and force a " +
                "SELECT in the database");

        entityManager.clear();

        Produto productToAssertAfterClearCache = entityManager.find(Produto.class, 2);

        Assertions.assertNotNull(productToAssertAfterClearCache);
    }

}
