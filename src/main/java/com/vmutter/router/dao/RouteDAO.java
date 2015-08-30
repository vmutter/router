package com.vmutter.router.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.vmutter.router.model.Route;

@Repository
public class RouteDAO {

    private EntityManagerFactory emf;

    private EntityManager em;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("routerPU");
        }

        if (em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public void insert(Route route) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            em.persist(route);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            tx.commit();
            em.close();
            emf.close();
        }
    }

    public Route findByName(String name) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            return em.createNamedQuery(Route.FIND_BY_NAME, Route.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

        return null;
    }
}
