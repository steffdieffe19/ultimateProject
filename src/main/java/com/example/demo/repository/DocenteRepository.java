package com.example.demo.repository;

<<<<<<< HEAD


import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

=======
import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
>>>>>>> Rest-Controller

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
<<<<<<< HEAD

    List<Docente> findAll();
=======
    @Query("SELECT d FROM Docente d WHERE d.nome = :nome AND d.cognome = :cognome")
    Optional<Docente> findByNomeAndCognome(@Param("nome") String nome, @Param("cognome") String cognome);
>>>>>>> Rest-Controller
}