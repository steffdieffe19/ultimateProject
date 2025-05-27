package com.example.demo.service;

import com.example.demo.Mapper.DocenteMapper;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.data.DTO.DocenteDTO;

@Service
@Transactional //serve per gestire bene le transazioni
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private DocenteMapper docenteMapper;

    public List<DocenteDTO> getAllDocenti() {
        List<Docente> docenti = docenteRepository.findAll();
        return docenteMapper.toDTOList(docenti);
    }

    public DocenteDTO getDocente(Long id) {
        Docente docente = docenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Docente non trovato"));
        return docenteMapper.toDTO(docente);
    }

    public DocenteDTO save(DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toEntity(docenteDTO);
        docenteRepository.save(docente);
        return docenteMapper.toDTO(docente);
    }

    public void delete(Long id) {
        docenteRepository.deleteById(id);
    }
}

