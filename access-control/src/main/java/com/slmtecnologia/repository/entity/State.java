package com.slmtecnologia.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "acronym")
    private String acronym;

    @NotBlank
    @Column(name = "name")
    private String name;

    public State() {    }

    public State(String acronym, String name) {
        this.acronym = acronym;
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(acronym, state.acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronym);
    }
}
