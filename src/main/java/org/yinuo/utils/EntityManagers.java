package org.yinuo.utils;

import javafx.util.Callback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

public class EntityManagers<T> {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory entityManagerFactory() {
        if (entityManagerFactory != null) {
            return entityManagerFactory;
        }
        synchronized (EntityManagers.class){
            if (entityManagerFactory != null) {
                return entityManagerFactory;
            }
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-unit");
            return entityManagerFactory;
        }
    }

    public static <T> T query(Function<EntityManager,T> callback){
        EntityManager entityManager = entityManagerFactory().createEntityManager();
        try{
            return callback.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    public static <T> T execute(Function<EntityManager,T> callback){
        EntityManager entityManager = entityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            T apply = callback.apply(entityManager);
            entityManager.getTransaction().commit();

            return apply;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }

    public static void execute(Consumer<EntityManager> consumer){
        execute(entityManager -> {
            consumer.accept(entityManager);
            return null;
        });
    }
}
