package com.vmutter.router.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.vmutter.router.model.Trace;

@Repository
public class TraceDAO {

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

    public void insert(Trace trace) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            em.persist(trace);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            tx.commit();
            em.close();
            emf.close();
        }
    }

    public Trace update(Trace trace) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            return em.merge(trace);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            tx.commit();
            em.close();
            emf.close();
        }

        return null;
    }

    public Trace findById(Long id) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            return em.find(Trace.class, id);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

        return null;
    }

    public Trace findByOriginDestination(Trace trace) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            return em.createNamedQuery(Trace.FIND_BY_ORIGIN_DESTINATION, Trace.class)
                    .setParameter("origin", trace.getOrigin()).setParameter("destination", trace.getDestination())
                    .getSingleResult();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

        return null;
    }
}
