package com.vmutter.router.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmutter.router.dao.TraceDAO;
import com.vmutter.router.exception.RouterException;
import com.vmutter.router.model.Trace;

@Service
public class TraceService {

    @Autowired
    private TraceDAO traceDAO;

    public void insert(Trace trace) {
        Trace t = traceDAO.findByOriginDestination(trace);
        if (t != null) {
            throw new RouterException("Trace already exists.");
        }

        traceDAO.insert(trace);
    }

    public Trace update(Trace trace) {
        Trace t = traceDAO.findByOriginDestination(trace);
        if (t == null) {
            throw new RouterException("Trace not found.");
        }

        t.setDistance(trace.getDistance());
        return traceDAO.update(t);
    }

    public List<Trace> findAll() {
        return traceDAO.findAll();
    }
}
