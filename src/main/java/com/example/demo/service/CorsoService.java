package com.example.demo.service;

import com.example.demo.Mapper.CorsoMapper;
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.data.entity.Discente;
import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final DiscenteRepository discenteRepository;
    private final CorsoMapper corsoMapper;

    private static final int NUMERO_MASSIMO_DISCENTI = 50;
    private static final int NUMERO_MINIMO_DISCENTI = 5;
    private static final int ANNI_ACCADEMICI_VALIDI_START = 2020;
    private static final int ANNI_ACCADEMICI_VALIDI_END = 2030;

    @Autowired
    public CorsoService(CorsoRepository corsoRepository,
                        DocenteRepository docenteRepository,
                        DiscenteRepository discenteRepository,
                        CorsoMapper corsoMapper) {
        this.corsoRepository = corsoRepository;
        this.docenteRepository = docenteRepository;
        this.discenteRepository = discenteRepository;
        this.corsoMapper = corsoMapper;
    }

    public List<CorsoDTO> findAllByNome(String nome) {
        List<Corso> corsi = corsoRepository.findByNome(nome);
        return corsoMapper.toDTOList(corsi);

    }

    public List<CorsoDTO> getAllCorsi() {
        List<Corso> corsi = corsoRepository.findAll();
        return corsoMapper.toDTOList(corsi);
    }

    public CorsoDTO getCorso(Long id) {
        Corso corso = findCorsoById(id);
        return corsoMapper.toDTO(corso);
    }


    public Corso get(Long id) {
        return findCorsoById(id);
    }

    private Corso findCorsoById(Long id) {
        return corsoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con id: " + id));
    }

    public void save(CorsoDTO corsoDTO) {
        validateCorsoDTO(corsoDTO);
        validateBusinessRules(corsoDTO);

        Corso corso = corsoMapper.toEntity(corsoDTO);

        Docente docente = docenteRepository.findById(corsoDTO.getDocenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con id: " + corsoDTO.getDocenteId()));
        corso.setDocente(docente);

        if (corsoDTO.getDiscentiIds() != null && !corsoDTO.getDiscentiIds().isEmpty()) {
            List<Discente> discenti = discenteRepository.findAllById(corsoDTO.getDiscentiIds());
            validateDiscenti(discenti, docente);
            corso.setDiscenti(discenti);
        }
        corsoRepository.save(corso);
    }

    private void validateDiscenti(List<Discente> discenti, Docente docente) {
    }

    public void delete(Long id) {
        Corso corso = findCorsoById(id);
        corsoRepository.delete(corso);
    }

    public List<Docente> getAllDocenti() {
        return docenteRepository.findAll();
    }

    public List<CorsoDTO> getCorsiByDocenteId(Long docenteId) {
        docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con id: " + docenteId));
        List<Corso> corsi = corsoRepository.findByDocenteId(docenteId);
        return corsoMapper.toDTOList(corsi);
    }

    private void validateCorsoDTO(CorsoDTO corsoDTO) {
        if (corsoDTO == null) {
            throw new IllegalArgumentException("Il corso non può essere null");
        }
        if (corsoDTO.getNome() == null || corsoDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del corso è obbligatorio");
        }
        if (corsoDTO.getAnno_accademico() == null) {
            throw new IllegalArgumentException("L'anno accademico è obbligatorio");
        }
        if (corsoDTO.getDocenteId() == null) {
            throw new IllegalArgumentException("Il docente è obbligatorio");
        }
    }

    private void validateBusinessRules(CorsoDTO corsoDTO) {
        if (corsoDTO.getAnno_accademico() < ANNI_ACCADEMICI_VALIDI_START ||
                corsoDTO.getAnno_accademico() > ANNI_ACCADEMICI_VALIDI_END) {
            throw new BusinessLogicException("L'anno accademico deve essere compreso tra " +
                    ANNI_ACCADEMICI_VALIDI_START + " e " + ANNI_ACCADEMICI_VALIDI_END);
        }
        Docente docente = docenteRepository.findById(corsoDTO.getDocenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato"));

        if (corsoDTO.getDiscentiIds() != null) {
            if (corsoDTO.getDiscentiIds().size() > NUMERO_MASSIMO_DISCENTI) {
                throw new BusinessLogicException("Il numero di discenti non può superare " + NUMERO_MASSIMO_DISCENTI);
            }
            if (corsoDTO.getDiscentiIds().size() < NUMERO_MINIMO_DISCENTI) {
                throw new BusinessLogicException("Il numero di discenti non può essere inferiore a " + NUMERO_MINIMO_DISCENTI);
            }
        }
    }
        private void validateDeletion (Corso corso){
            if (!corso.getDiscenti().isEmpty()) {
                throw new BusinessLogicException("Non è possibile eliminare un corso con discenti associati");
            }
        }
    }