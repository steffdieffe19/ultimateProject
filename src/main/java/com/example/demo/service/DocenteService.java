package com.example.demo.service;

import com.example.demo.Mapper.DocenteMapper;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.data.DTO.DocenteDTO;

@Service
@Transactional
public class DocenteService {

    private DocenteRepository docenteRepository;
    private DocenteMapper docenteMapper;

    @Autowired
    public DocenteService(DocenteRepository docenteRepository, DocenteMapper docenteMapper) {
        this.docenteRepository = docenteRepository;
        this.docenteMapper = docenteMapper;
    }

    public List<DocenteDTO> getAllDocenti() {
        List<Docente> docenti = docenteRepository.findAll();
        return docenteMapper.toDTOList(docenti);
    }

    public DocenteDTO getDocente(Long id) {
        Docente docente = docenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Docente non trovato"));
        return docenteMapper.toDTO(docente);
    }

    public DocenteDTO save(DocenteDTO docenteDTO) {
        validateDocente(docenteDTO);
        Docente docente = docenteMapper.toEntity(docenteDTO);

        if (docente.getId() != null) {
            docenteRepository.findById(docente.getId()).orElseThrow(() -> new RuntimeException("Docente non trovato"));
        }
        docenteRepository.save(docente);
        return docenteMapper.toDTO(docente);
    }

    public void delete(Long id) {

        if (!docenteRepository.existsById(id)) {
            throw new RuntimeException("Docente non trovato");
        }
        docenteRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return docenteRepository.existsById(id);
    }
    public List<DocenteDTO> findByNome(String nome) {
        List<Docente> docenti = docenteRepository.findByNome(nome);
        return docenteMapper.toDTOList(docenti);
    }
    public List<DocenteDTO> getDocentiByCorsoId(Long corsoId) {
        List<Docente> docenti = docenteRepository.findByCorsiId(corsoId);
        return docenteMapper.toDTOList(docenti);
    }
    private void validateDocente(DocenteDTO docenteDTO) {
        if (docenteDTO == null ) {
            throw new IllegalArgumentException("DocenteDTO non può essere null");
        }
        if (docenteDTO.getNome() == null ) {
            throw new IllegalArgumentException("Nome non può essere null");
        }
    }


}

