package com.slmtecnologia.repository;

import com.slmtecnologia.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u " +
            "JOIN u.roles r " +
            "JOIN r.applications a " +
            "WHERE u.email = :email " +
            "AND a.appCode = :appCode")
    Optional<User> findByEmailAndCodApp(@Param("email") String email, @Param("appCode") Long appCode);

    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT ('%',:firstName,'%'))")
    Page<User> findByName(@Param("firstName") String firstName, Pageable pageable);

}
