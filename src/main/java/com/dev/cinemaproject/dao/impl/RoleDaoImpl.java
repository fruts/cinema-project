package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.RoleDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.model.Role;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session
                    .createQuery("from Role r "
                            + " where r.roleName =: roleName", Role.class);
            query.setParameter("roleName", Role.RoleName.valueOf(roleName));
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find role with name "
                    + roleName, e);
        }
    }

    @Override
    public Role add(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add role", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Role> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> roles =
                    session.createQuery("FROM Role ", Role.class);
            return roles.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get all roles", e);
        }
    }

    @Override
    public Role getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Role.class, id);
        }
    }
}
