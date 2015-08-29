package com.vmutter.router.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.vmutter.router.model.Trace;

@Repository
public class TraceDAO {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("routerPU");
    EntityManager em = factory.createEntityManager();
    
    public Trace findById(Long id) {
        return em.find(Trace.class, id);
    }
    
    public void insert(Trace trace) {
        em.persist(trace);
    }
}
