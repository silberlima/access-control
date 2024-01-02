package com.slmtecnologia.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "city",schema = "access_control")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cityId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "ibge_code")
    private String ibgeCode;

    @ManyToOne
    @JoinColumn(name = "acronym")
    private State state;

    public City() {
    }

    public City(Long id, String name, String ibgeCode, State state) {
        this.id = id;
        this.name = name;
        this.ibgeCode = ibgeCode;
        this.state = state;
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

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
