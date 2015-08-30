package com.vmutter.router.rs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.vmutter.router.exception.RouterException;
import com.vmutter.router.model.Node;
import com.vmutter.router.model.Route;
import com.vmutter.router.pojo.RouteResponse;
import com.vmutter.router.service.RouteService;

@Component
@Path("/route")
public class RouteRS {

    @Autowired
    private RouteService routeService;

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response add(@QueryParam("name") String name, @QueryParam("origin") String origin,
            @QueryParam("destination") String destination) {
        try {
            Route route = new Route(name, new Node(origin), new Node(destination));

            routeService.insert(route);
            return Response.ok().build();
        } catch (RouterException re) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(re.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoute(@QueryParam("origin") String origin, @QueryParam("destination") String destination,
            @QueryParam("autonomy") Double autonomy, @QueryParam("cost") Double cost) {
        try {
            RouteResponse r = routeService.calculateRoute(origin, destination, autonomy, cost);

            return Response.ok(new Gson().toJson(r)).build();
        } catch (RouterException re) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(re.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
