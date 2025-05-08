package com.example.demo.service;

import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DocenteService {


    @Autowired
    DocenteRepository docenteRepository;

    public List<Docente> findAllSortedByData_di_nascitaDesc() throws SQLException {
        return docenteRepository.findAllSortedByData_di_nascitaDesc();
    }

    public Docente get(Long id) {
        return docenteRepository.findById(id).orElseThrow();
    }

    public void save(Docente d) {docenteRepository.save(d);
    }

    public void delete(Long id) {
        docenteRepository.deleteById(id);
    }
}
