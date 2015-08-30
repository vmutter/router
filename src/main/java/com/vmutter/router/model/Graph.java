package com.vmutter.router.model;

import java.util.List;

/**
 * Simple POJO representing a graph of nodes and traces.
 * 
 * @author vmutter
 *
 */
public class Graph {

    private final List<Node> nodes;
    private final List<Trace> traces;

    public Graph(List<Node> nodes, List<Trace> traces) {
        this.nodes = nodes;
        this.traces = traces;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Trace> getTraces() {
        return traces;
    }

}
