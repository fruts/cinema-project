package com.dev.cinemaproject.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {

    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Unable to create SESSION FACTORY", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
