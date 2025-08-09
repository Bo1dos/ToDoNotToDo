package com.task.manager.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            // ЯВНО РЕГИСТРИРУЕМ АННОТИРОВАННЫЕ КЛАССЫ
            cfg.addAnnotatedClass(com.task.manager.domain.model.User.class);
            cfg.addAnnotatedClass(com.task.manager.domain.model.Task.class);

            StandardServiceRegistryBuilder builder = 
                new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());

            return cfg.buildSessionFactory(builder.build());

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        sessionFactory.getMetamodel().getEntities()
                        .forEach(e -> System.out.println("ENTITY: " + e.getName() + " -> " + e.getJavaType()));

        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}


/*
 * try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    session.beginTransaction();
    // ... ваши операции: save, get, query ...
    session.getTransaction().commit();
}
 */