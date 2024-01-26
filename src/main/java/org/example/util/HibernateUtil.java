package org.example.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//https://www.javaguides.net/2018/11/hibernate-5-xml-configuration-example.html
public class HibernateUtil {


    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Private constructor to prevent instantiation
    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception ex) {
            // Handle the exception (e.g., log it)
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }



}
