package com.example.demo.repository;



import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    List<Docente> findAll();
}