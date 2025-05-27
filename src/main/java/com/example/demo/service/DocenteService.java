package com.example.demo.service;

<<<<<<< HEAD
import com.example.demo.Mapper.DocenteMapper;
=======
>>>>>>> Rest-Controller
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.DTO.DocenteDTO;

@Service
@Transactional //serve per gestire bene le transazioni
public class DocenteService {

<<<<<<< HEAD
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
=======
   private final DocenteRepository docenteRepository;

    @Autowired
    public DocenteService(DocenteRepository docenteRepository){

        this.docenteRepository=docenteRepository;
    }
    public List<Docente> getAllDocenti()  {
        return docenteRepository.findAll();
    }

    public Optional<Docente> getDocenteById(Long id) {
        return docenteRepository.findById(id);
    }

    public Docente createDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    public Docente updateDocente(Docente docente) {
        Docente existing = docenteRepository.findById(docente.getId())
                .orElseThrow(() -> new RuntimeException("Docente non trovato con id: " + docente.getId()));

        existing.setNome(docente.getNome());
        existing.setCognome(docente.getCognome());

        return docenteRepository.save(existing);
>>>>>>> Rest-Controller
    }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));
        docenteRepository.delete(docente);
    }


}

