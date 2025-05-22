package com.example.demo.repository;

import com.example.demo.data.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long> {
    List<Corso> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT c FROM Corso c WHERE c.docente.nome = :nome AND c.docente.cognome = :cognome")
    List<Corso> findByDocenteNomeAndCognome(
        @Param("nome") String nome,
        @Param("cognome") String cognome
    );
    
    @Query("SELECT c FROM Corso c LEFT JOIN FETCH c.docente LEFT JOIN FETCH c.discenti")
    List<Corso> findAllWithDetails();
}