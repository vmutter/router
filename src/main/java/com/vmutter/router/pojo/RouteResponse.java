package com.vmutter.router.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vmutter.router.model.Node;

public class RouteResponse {

    private List<String> route;

    private Double cost;

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setRoutePath(List<Node> nodes) {
        route = new ArrayList<String>();
        nodes.stream().forEach(n -> route.add(n.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RouteResponse other = (RouteResponse) obj;
        return Objects.equals(route, other.route) && Objects.equals(cost, other.cost);
    }

    @Override
    public String toString() {
        return "RouteResponse [route=" + route + ", cost=" + cost + "]";
    }
}
