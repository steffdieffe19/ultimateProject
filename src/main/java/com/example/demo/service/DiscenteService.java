package com.example.demo.service;

import com.example.demo.Mapper.DiscenteMapper;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private CorsoRepository corsoRepository;

    private final DiscenteMapper discenteMapper;

    @Autowired
    public DiscenteService(DiscenteMapper discenteMapper) {
        this.discenteMapper = discenteMapper;
    }

    public List<DiscenteDTO> getAllDiscenti() {
        List<Discente> discenti = discenteRepository.findAll();
        return discenteMapper.toDTOList(discenti);
    }

    public DiscenteDTO get(Long id) {
        Discente discente = discenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Discente non trovato"));
        return discenteMapper.toDTO(discente);
    }
    public DiscenteDTO save (DiscenteDTO discenteDTO){
        Discente discente = discenteMapper.toEntity(discenteDTO);

        if (discente.getCitta_di_residenza() == null || discente.getCitta_di_residenza().isEmpty()) {
            // Imposta un valore di default se il campo è vuoto
            discente.setCitta_di_residenza("Città sconosciuta");  // Modifica con un valore di default appropriato
        }
        if(discenteDTO.getCorsiIds()!=null){
            List<Corso> corsi= corsoRepository.findAllById(discenteDTO.getCorsiIds());
            discente.setCorsi(corsi);
        }
        discenteRepository.save(discente);
        return discenteMapper.toDTO(discente);
    }
    public void delete(Long id) {
        discenteRepository.deleteById(id);;
    }

}
