package com.vmutter.router.rs;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmutter.router.exception.RouterException;
import com.vmutter.router.model.Trace;
import com.vmutter.router.service.TraceService;

@Component
@Path("/trace")
public class TraceRS {

    @Autowired
    private TraceService traceService;

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response add(@QueryParam("origin") String origin, @QueryParam("destination") String destination,
            @QueryParam("distance") Double distance) {
        try {
            Trace trace = new Trace(origin, destination, distance);

            traceService.insert(trace);

            return Response.ok().build();
        } catch (RouterException re) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(re.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@QueryParam("origin") String origin, @QueryParam("destination") String destination,
            @QueryParam("distance") Double distance) {
        try {
            Trace trace = new Trace(origin, destination, distance);

            traceService.update(trace);

            return Response.ok().build();
        } catch (RouterException re) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(re.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
