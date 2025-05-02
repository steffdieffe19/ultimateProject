package com.example.demo.repository;


import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscenteRepository extends JpaRepository<Discenete, Long> {


    @Query("SELECT d FROM Discente d WHERE d.nome = ?1")
    List<Discente> findByNome(String nome);
}

