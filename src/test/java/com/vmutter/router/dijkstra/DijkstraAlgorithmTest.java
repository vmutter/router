package com.vmutter.router.dijkstra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.vmutter.router.model.Graph;
import com.vmutter.router.model.Node;
import com.vmutter.router.model.Trace;

/**
 * Test for the Dijkstra's Algorithm implementation.
 * 
 * @author vmutter
 *
 */
@RunWith(JUnit4.class)
public class DijkstraAlgorithmTest {

    /**
     * Test the email example.
     */
    @Test
    public void testExample() {
        List<Node> nodes = new ArrayList<Node>();
        List<Trace> traces = new ArrayList<Trace>();

        Node nodeA = new Node(1l, "A");
        Node nodeB = new Node(2l, "B");
        Node nodeC = new Node(3l, "C");
        Node nodeD = new Node(4l, "D");
        Node nodeE = new Node(5l, "E");

        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodes.add(nodeD);
        nodes.add(nodeE);

        traces.add(new Trace(nodeA, nodeB, 10.0));
        traces.add(new Trace(nodeB, nodeD, 15.0));
        traces.add(new Trace(nodeA, nodeC, 20.0));
        traces.add(new Trace(nodeC, nodeD, 30.0));
        traces.add(new Trace(nodeB, nodeE, 50.0));
        traces.add(new Trace(nodeD, nodeE, 30.0));

        Graph graph = new Graph(nodes, traces);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodeA);
        LinkedList<Node> path = dijkstra.getPath(nodeD);

        for (Node vertex : path) {
            System.out.println(vertex);
        }

        assertNotNull(path);
        assertEquals("A", path.get(0).getName());
        assertEquals("B", path.get(1).getName());
        assertEquals("C", path.get(2).getName());
    }
}
