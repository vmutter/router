package com.vmutter.router.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmutter.router.dao.RouteDAO;
import com.vmutter.router.dijkstra.DijkstraAlgorithm;
import com.vmutter.router.model.Graph;
import com.vmutter.router.model.Node;
import com.vmutter.router.model.Route;
import com.vmutter.router.model.Trace;

@Service
public class RouteService {

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private TraceService traceService;

    public void insert(Route route) {
        routeDAO.insert(route);
    }

    public List<Node> calculate(String name, Double autonomy, Double cost) {
        Route route = routeDAO.findByName(name);

        List<Node> nodes = nodeService.findAll();
        List<Trace> traces = traceService.findAll();

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, traces);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        
        Node startNode = nodes.stream().filter(n -> n.getName().equals(route.getOrigin().getName())).findFirst().get();
        Node endNode = nodes.stream().filter(n -> n.getName().equals(route.getDestination().getName())).findFirst().get();
        
        dijkstra.execute(startNode);
        LinkedList<Node> path = dijkstra.getPath(endNode);

        return path;
    }
}
