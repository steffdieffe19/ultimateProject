package com.example.demo.repository;

import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    @Query("SELECT d FROM Docente d WHERE d.nome = :nome AND d.cognome = :cognome")
    Optional<Docente> findByNomeAndCognome(@Param("nome") String nome, @Param("cognome") String cognome);
}