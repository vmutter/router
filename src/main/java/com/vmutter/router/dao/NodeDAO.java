package com.vmutter.router.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vmutter.router.model.Node;
import com.vmutter.router.model.Trace;

@Repository
public class NodeDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Node node) {
        em.persist(node);
    }

    public Trace findById(Long id) {
        return em.find(Trace.class, id);
    }

    public Node findByName(String name) {
        try {
            return em.createNamedQuery(Node.FIND_BY_NAME, Node.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Node> findAll() {
        return em.createNamedQuery(Node.FIND_ALL, Node.class).getResultList();
    }
}
