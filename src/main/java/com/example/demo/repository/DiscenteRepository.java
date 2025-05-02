package com.example.demo.repository;


import com.example.demo.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {


    @Query("SELECT d FROM Discente d WHERE d.nome = ?1")
    List<Discente> findByNome(String nome);
}

