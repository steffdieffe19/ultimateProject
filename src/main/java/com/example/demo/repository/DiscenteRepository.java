package com.example.demo.repository;

import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {

    @Query("SELECT d FROM Discente d WHERE :corso NOT MEMBER OF d.corsi")
    List<Discente> findDiscentiNotInCorso(@Param("corso") Corso corso);
}