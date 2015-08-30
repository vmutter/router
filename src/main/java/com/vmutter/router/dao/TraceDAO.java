package com.vmutter.router.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vmutter.router.model.Node;
import com.vmutter.router.model.Trace;

@Repository
@Transactional
public class TraceDAO {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NodeDAO nodeDAO;

    public void insert(Trace trace) {
        Node nodeOrigin = nodeDAO.findByName(trace.getOrigin().getName());
        Node nodeDest = nodeDAO.findByName(trace.getDestination().getName());

        if (nodeOrigin != null) {
            trace.setOrigin(nodeOrigin);
        }

        if (nodeDest != null) {
            trace.setDestination(nodeDest);
        }

        em.persist(trace);
    }

    public Trace update(Trace trace) {
        return em.merge(trace);
    }

    public Trace findById(Long id) {
        return em.find(Trace.class, id);
    }

    public Trace findByOriginDestination(Trace trace) {
        try {
            return em.createNamedQuery(Trace.FIND_BY_ORIGIN_DESTINATION, Trace.class)
                    .setParameter("originName", trace.getOrigin().getName())
                    .setParameter("destinationName", trace.getDestination().getName()).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Trace> findAll() {
        return em.createNamedQuery(Trace.FIND_ALL, Trace.class).getResultList();
    }
}
