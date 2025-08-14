package com.task.manager.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.task.manager.domain.model.Task;
import com.task.manager.domain.model.User;
import com.task.manager.utils.HibernateUtils;

public class UserRepositoryHibernate implements UserRepository{

    @Override
    public void add(User user) {

        Transaction ts = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.persist(user);
            ts.commit();
        } catch (Exception e) {
            if(ts != null) {ts.rollback();}
            throw e;
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from User",User.class)
                            .list();
        } catch (Exception e) {
            throw e;
        }
    }

    
    @Override
    public Optional<User> findByUsername(String username) {
       try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from User where username = :name";
            return Optional.ofNullable(session.createQuery(hql,User.class)
                            .setParameter("name", username)
                            .uniqueResult());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(User user) {
        Transaction ts = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            String hql = """
                            from User u set
                                u.username = coalesce(:username, u.username),
                                u.password_hash = coalesce(:password_hash, u.password_hash),
                                u.salt = coalesce(:salt, u.salt)
                            where u.id = :id
                        """;
            
            
            
            session.createQuery(hql, User.class)
                        .setParameter("username", user.getUsername())
                        .setParameter("password_hash", user.getPasswordHash())
                        .setParameter("salt", user.getSalt())
                        .setParameter("id", user.getId());

            ts.commit();
        } catch (Exception e) {
            if(ts != null) {ts.rollback();}
            throw e;
        }
    }

    @Override
    public void delete(UUID id) {
       Transaction ts = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            User u = session.get(User.class, id);
            if(u != null){
                session.remove(u);
            }
            ts.commit();
        } catch (Exception e) {
            if(ts != null) {ts.rollback();}
            throw e;
        }
    }

    @Override
    public Integer count() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(u) from User u";
            return session.createQuery(hql,Integer.class)
                            .uniqueResult();
        } catch (Exception e) {
            throw e;
        }
    }


   
}
