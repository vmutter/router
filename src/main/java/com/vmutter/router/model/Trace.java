package com.vmutter.router.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * Entity representating the distance between two nodes.
 * 
 * @author vmutter
 *
 */
@Entity
@Table(name = "trace")
@NamedQueries(value = {
        @NamedQuery(name = Trace.FIND_ALL, query = "SELECT t FROM Trace t"),
        @NamedQuery(name = Trace.FIND_BY_ORIGIN_DESTINATION, query = "SELECT t FROM Trace t WHERE t.origin.name = :originName AND t.destination.name = :destinationName") })
public class Trace {

    public static final String FIND_ALL = "findTraceAll";

    public static final String FIND_BY_ORIGIN_DESTINATION = "findTraceByOriginDestination";

    public Trace() {
        super();
    }

    public Trace(String origin, String destination, Double distance) {
        super();
        this.origin = new Node(null, origin);
        this.destination = new Node(null, destination);
        this.distance = distance;
    }

    public Trace(Node origin, Node destination, Double distance) {
        super();
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id")
    private Node origin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Node destination;

    private Double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, destination, distance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Trace other = (Trace) obj;

        return Objects.equals(id, other.id) && Objects.equals(origin, other.origin)
                && Objects.equals(destination, other.destination) && Objects.equals(distance, other.distance);
    }

    @Override
    public String toString() {
        return "Trace [id=" + id + ", origin=" + origin + ", destination=" + destination + ", distance=" + distance
                + "]";
    }

}
