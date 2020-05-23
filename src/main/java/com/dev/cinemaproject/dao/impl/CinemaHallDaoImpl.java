package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.CinemaHallDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.lib.Dao;
import com.dev.cinemaproject.model.CinemaHall;
import com.dev.cinemaproject.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger LOGGER = Logger.getLogger(CinemaHallDaoImpl.class);

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            LOGGER.info("Cinemahall:" + cinemaHall
                    + "was successfully added into DB");
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add cinema hall: "
                    + cinemaHall + " into DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<CinemaHall> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            LOGGER.info("METHOD GETALL WORKED SUCCESSFULLY");
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("UNABLE TO GET ALL CINEMAHALLS", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
