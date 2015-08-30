package com.vmutter.router.rs;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmutter.router.model.Trace;
import com.vmutter.router.service.TraceService;

@Component
@Path("/trace")
public class TraceRS {

    @Autowired
    private TraceService traceService;

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String add(@QueryParam("origin") String origin, @QueryParam("destination") String destination,
            @QueryParam("distance") Double distance) {
        Trace trace = new Trace(origin, destination, distance);

        traceService.insert(trace);

        return "ok";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String update(@QueryParam("origin") String origin, @QueryParam("destination") String destination,
            @QueryParam("distance") Double distance) {
        Trace trace = new Trace(origin, destination, distance);

        traceService.update(trace);

        return "ok";
    }
}
