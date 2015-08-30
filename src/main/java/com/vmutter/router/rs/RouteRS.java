package com.vmutter.router.rs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmutter.router.model.Node;
import com.vmutter.router.model.Route;
import com.vmutter.router.service.RouteService;

@Component
@Path("/route")
public class RouteRS {

    @Autowired
    private RouteService routeService;

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String add(@QueryParam("name") String name, @QueryParam("origin") String origin,
            @QueryParam("destination") String destination) {
        Route route = new Route(name, new Node(origin), new Node(destination));

        routeService.insert(route);

        return "ok";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRoute(@QueryParam("name") String name, @QueryParam("name") Double autonomy,
            @QueryParam("cost") Double cost) {
        routeService.calculate(name, autonomy, cost);

        return null;
    }
}
