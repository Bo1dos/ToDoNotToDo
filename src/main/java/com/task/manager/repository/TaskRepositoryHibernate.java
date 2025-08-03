package com.task.manager.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.task.manager.domain.model.Task;
import com.task.manager.domain.model.Task.TaskStatus;
import com.task.manager.utils.HibernateUtils;

public class TaskRepositoryHibernate implements TaskRepository {

    @Override
    public void add(Task task) {
        Transaction ts = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.persist(task);
            ts.commit();
        } catch (Exception e) {
            if(ts != null){ts.rollback();}
            throw e;
        }
    }

    @Override
    public Optional<Task> findById(UUID id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Task.class, id));
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<Task> findAll() {
       try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Task", Task.class)
                            .list();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Task> findByHeader(String header) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Task where header like: pattern";
            return session.createQuery(hql, Task.class).
                            setParameter("pattern", "%"+header+"%")
                            .list();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Task where taskStatus = :status";
            return session.createQuery(hql, Task.class).
                            setParameter("status", status)
                            .list();
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<Task> findOverdue() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "from Task where deadLine < :timeNow";
            return session.createQuery(hql, Task.class).
                            setParameter("timeNow", Instant.now())
                            .list();
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public void update(Task task) {
        Transaction ts = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.merge(task);
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
            Task t = session.get(Task.class, id);
            if (t != null) {
                session.remove(t);
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
            String hql = "SELECT COUNT(t) from Task t";
            return session.createQuery(hql, Integer.class).uniqueResult();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer countDone() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(t) from Task t where t.taskStatus = :doneStatus";
            return session.createQuery(hql, Integer.class)
                    .setParameter(hql, TaskStatus.DONE)
                    .uniqueResult();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer countUndone() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(t) from Task t where t.taskStatus = :undoneStatus";
            return session.createQuery(hql, Integer.class)
                    .setParameter(hql, TaskStatus.UNDONE)
                    .uniqueResult();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer countOverdue() {
       try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(T) from Task t where t.deadLine < :timeNow";
            return session.createQuery(hql, Integer.class)
                            .setParameter("timeNow", Instant.now())
                            .uniqueResult();
        } catch (Exception e) {
            throw e;
        }
    }

}
