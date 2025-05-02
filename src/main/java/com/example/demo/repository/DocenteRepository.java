package com.example.demo.repository;



import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {


    @Query("SELECT d FROM Docente d WHERE d.nome = ?1")
    List<Docente> findByNome(String nome);
}
