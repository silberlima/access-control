package com.slmtecnologia.repository;

import com.slmtecnologia.model.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.id = :id ")
    Optional<Person> findById(Long id);

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT ('%',:name,'%'))")
    Page<Person> findByName(@Param("name") String name, Pageable pageable);
}
