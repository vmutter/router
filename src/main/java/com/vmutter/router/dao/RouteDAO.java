package com.vmutter.router.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vmutter.router.model.Node;
import com.vmutter.router.model.Route;

@Repository
public class RouteDAO {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NodeDAO nodeDAO;

    public void insert(Route route) {
        Node nodeOrigin = nodeDAO.findByName(route.getOrigin().getName());
        Node nodeDest = nodeDAO.findByName(route.getDestination().getName());

        if (nodeOrigin != null) {
            route.setOrigin(nodeOrigin);
        }

        if (nodeDest != null) {
            route.setDestination(nodeDest);
        }

        em.persist(route);
    }

    public Route findByName(String name) {
        try {
            return em.createNamedQuery(Route.FIND_BY_NAME, Route.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
