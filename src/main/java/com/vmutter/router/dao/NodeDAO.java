package com.vmutter.router.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.vmutter.router.model.Node;
import com.vmutter.router.model.Trace;

@Repository
public class NodeDAO {

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

    public void insert(Node node) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            em.persist(node);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            tx.commit();
            em.close();
            emf.close();
        }
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

    public Node findByName(String name) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            return em.createNamedQuery(Node.FIND_BY_NAME, Node.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

        return null;
    }

    public List<Node> findAll() {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            return em.createNamedQuery(Node.FIND_ALL, Node.class).getResultList();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

        return null;
    }
}
