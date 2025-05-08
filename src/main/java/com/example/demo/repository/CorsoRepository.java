package com.example.demo.repository;

import com.example.demo.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorsoRepository extends JpaRepository<Corso, Long> {

    @Query("SELECT c FROM Corso c ORDER BY c.nome")
    List<Corso> findAllSortedByNome();

    @Query("SELECT c FROM Corso c JOIN FETCH c.docente")
    List<Corso> findAllWithDocente();
}