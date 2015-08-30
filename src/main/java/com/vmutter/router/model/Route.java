package com.vmutter.router.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String name;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name="route_trace", joinColumns = @JoinColumn( name="route_id"), inverseJoinColumns = @JoinColumn( name="trace_id"))
    private List<Trace> traces;

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Route [id=" + id + "]";
    }
}
