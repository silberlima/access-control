package com.slmtecnologia.model.entity;

import jakarta.persistence.*;
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
@Table(name = "city")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "ibge_code")
    private String ibgeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acronym")
    private State state;

}
