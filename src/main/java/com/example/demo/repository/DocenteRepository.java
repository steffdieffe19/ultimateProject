package com.example.demo.repository;

import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {


    @Query("SELECT d FROM Docente d ORDER BY d.data_di_nascita desc")
    Optional<Docente> findById(Integer id);
}