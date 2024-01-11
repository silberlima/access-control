package com.slmtecnologia.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "personId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;
    @Column(length = 80)
    private String socialName;
    @Column(length = 80)
    private String fatherName;
    @Column(length = 80)
    private String motherName;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(length = 80)
    private LocalDate birthDate;
    @Column(nullable = false, length = 80)
    private String email;
    private String street;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private City city;
    private String zipCode;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Integer createBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;

}