package com.vmutter.router.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmutter.router.dao.RouteDAO;
import com.vmutter.router.dijkstra.DijkstraAlgorithm;
import com.vmutter.router.exception.RouterException;
import com.vmutter.router.model.Node;
import com.vmutter.router.model.Route;
import com.vmutter.router.model.Trace;
import com.vmutter.router.pojo.Graph;
import com.vmutter.router.pojo.RouteResponse;

@Service
public class RouteService {

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private TraceService traceService;

    public void insert(Route route) {
        Route r = routeDAO.findByName(route.getName());
        if (r != null) {
            throw new RouterException("Route already exists.");
        }

        routeDAO.insert(route);
    }

    public RouteResponse calculateRoute(String origin, String destination, Double autonomy, Double fuelCost) {
        RouteResponse routeResponse = new RouteResponse();

        List<Node> nodes = nodeService.findAll();
        List<Trace> traces = traceService.findAll();

        Graph graph = new Graph(nodes, traces);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

        Optional<Node> startNode = nodes.stream().filter(n -> n.getName().equals(origin)).findFirst();
        Optional<Node> endNode = nodes.stream().filter(n -> n.getName().equals(destination)).findFirst();

        if (!startNode.isPresent() || !endNode.isPresent()) {
            throw new RouterException("Invalid Node");
        }

        dijkstra.execute(startNode.get());
        LinkedList<Node> path = dijkstra.getPath(endNode.get());

        Double km = calculateRouteKm(path, traces);

        routeResponse.setRoutePath(path);
        routeResponse.setCost(calculateRouteCost(km, autonomy, fuelCost));

        return routeResponse;
    }

    private Double calculateRouteKm(List<Node> nodes, List<Trace> traces) {
        Double km = 0.0;

        Trace trace = null;

        for (int i = 0; i < nodes.size() - 1; i++) {

            for (Trace t : traces) {
                if (t.getOrigin().getName().equals(nodes.get(i).getName())
                        && t.getDestination().getName().equals(nodes.get(i + 1).getName())) {
                    trace = t;
                }
            }

            if (trace != null) {
                km += trace.getDistance();
            } else {
                throw new RouterException("Trace not found for route");
            }

            trace = null;
        }

        return km;
    }

    private Double calculateRouteCost(Double km, Double autonomy, Double fuelCost) {
        return (km / autonomy) * fuelCost;
    }
}
