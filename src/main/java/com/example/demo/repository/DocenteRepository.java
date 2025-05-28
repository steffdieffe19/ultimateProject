package com.example.demo.repository;

import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    // Il metodo findAll() è già fornito da JpaRepository

    @Query("SELECT d FROM Docente d WHERE d.nome = :nome AND d.cognome = :cognome")
    Optional<Docente> findByNomeAndCognome(@Param("nome") String nome, @Param("cognome") String cognome);
}