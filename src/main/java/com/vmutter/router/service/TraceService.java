package com.vmutter.router.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmutter.router.dao.TraceDAO;
import com.vmutter.router.model.Trace;

@Service
public class TraceService {

    @Autowired
    private TraceDAO traceDAO;

    public void insert(Trace trace) {
        traceDAO.insert(trace);
    }

    public Trace update(Trace trace) {
        Trace t = traceDAO.findByOriginDestination(trace);

        t.setDistance(trace.getDistance());
        return traceDAO.update(t);
    }
}
