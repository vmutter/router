package com.vmutter.router.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vmutter.router.model.Route;

@Repository
public class RouteDAO {

    @PersistenceContext
    private EntityManager em;

    public void insert(Route route) {
        em.persist(route);

    }

    public Route findByName(String name) {
        return em.createNamedQuery(Route.FIND_BY_NAME, Route.class).setParameter("name", name).getSingleResult();
    }
}
