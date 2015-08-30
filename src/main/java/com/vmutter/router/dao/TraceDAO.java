package com.vmutter.router.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vmutter.router.model.Trace;

@Repository
@Transactional
public class TraceDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Trace trace) {
        em.persist(trace);
    }

    public Trace update(Trace trace) {
        return em.merge(trace);
    }

    public Trace findById(Long id) {
        return em.find(Trace.class, id);
    }

    public Trace findByOriginDestination(Trace trace) {
        return em.createNamedQuery(Trace.FIND_BY_ORIGIN_DESTINATION, Trace.class)
                .setParameter("origin", trace.getOrigin()).setParameter("destination", trace.getDestination())
                .getSingleResult();
    }

    public List<Trace> findAll() {
        return em.createNamedQuery(Trace.FIND_ALL, Trace.class).getResultList();
    }
}
