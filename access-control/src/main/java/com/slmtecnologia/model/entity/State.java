package com.slmtecnologia.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
