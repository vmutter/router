package com.vmutter.router.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "route")
@NamedQueries(value = { @NamedQuery(name = Route.FIND_BY_NAME, query = "SELECT r FROM Route r WHERE r.name = :name") })
public class Route {

    public static final String FIND_BY_NAME = "findRouteByName";

    public Route() {
        super();
    }

    public Route(String name, Node origin, Node destination) {
        super();
        this.name = name;
        this.origin = origin;
        this.destination = destination;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Node origin;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Node destination;

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

    public Node getOrigin() {
        return origin;
    }

    public void setOrigin(Node origin) {
        this.origin = origin;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origin, destination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Route other = (Route) obj;

        return Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(origin, other.destination);
    }

    @Override
    public String toString() {
        return "Route [id=" + id + ", name=" + name + ", origin=" + origin + ", destination=" + destination + "]";
    }
}
