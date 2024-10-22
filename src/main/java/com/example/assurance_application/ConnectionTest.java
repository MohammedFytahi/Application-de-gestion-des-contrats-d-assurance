package com.example.assurance_application;

import com.example.assurance_application.config.AppConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConnectionTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = null;

        try {

            entityManager = entityManagerFactory.createEntityManager();


            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            entityManager.getTransaction().commit();


            System.out.println("Database connection successful!");

        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            ((AnnotationConfigApplicationContext) context).close();
        }
    }
}
