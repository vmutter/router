package com.vmutter.router.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity class representing a node to be used on the Dijkstra's algorithm.
 * 
 * @author vmutter
 *
 */
@Entity
@Table(name = "node")
@NamedQueries(value = { @NamedQuery(name = Node.FIND_ALL, query = "SELECT n FROM Node n"),
        @NamedQuery(name = Node.FIND_BY_NAME, query = "SELECT n FROM Node n WHERE n.name = :name") })
public class Node {

    public static final String FIND_ALL = "findNodeAll";

    public static final String FIND_BY_NAME = "findNodeByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    public Node() {
        super();
    }

    public Node(String name) {
        super();
        this.name = name;
    }

    public Node(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Node other = (Node) obj;

        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Node [id=" + id + ", name=" + name + "]";
    }
}
