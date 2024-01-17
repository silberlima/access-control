package com.slmtecnologia.repository;

import com.slmtecnologia.model.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
    @Query("SELECT a FROM Application a WHERE LOWER(a.name) LIKE LOWER(CONCAT ('%',:name,'%'))")
    Page<Application> findByName(@Param("name") String name, Pageable pageable);
}
