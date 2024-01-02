package com.slmtecnologia.repository;

import com.slmtecnologia.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, String> {
}
