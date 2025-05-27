package com.example.demo.service;

<<<<<<< HEAD
import com.example.demo.Mapper.DiscenteMapper;
import com.example.demo.data.DTO.DiscenteDTO;
=======

>>>>>>> Rest-Controller
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {

    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;

    @Autowired
<<<<<<< HEAD
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
=======
    public DiscenteService(DiscenteRepository discenteRepository, CorsoRepository corsoRepository) {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
    }

    public List<Discente> getAllDiscenti() {
        return discenteRepository.findAll();
    }

    public Optional<Discente> getDiscenteById(Long id) {
        return discenteRepository.findById(id);
    }

    public Optional<Discente> getDiscenteByNomeAndCognome(String nome, String cognome) {
        return discenteRepository.findByNomeAndCognome(nome, cognome);
    }

    public Discente createDiscente(Discente discente) {
        discenteRepository.findByMatricola(discente.getMatricola()).ifPresent(existing -> {
            throw new RuntimeException("Matricola già esistente: " + discente.getMatricola());
        });

        return discenteRepository.save(discente);
    }

    public void delete(Long id) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente non trovato"));
        discenteRepository.delete(discente);
    }
    public Discente aggiungiCorso (Long id_discente, Long id_corso) {
        Discente discente = discenteRepository.findById(id_discente)
                .orElseThrow(() -> new RuntimeException("Discente non trovato" + id_discente));
        Corso corso = corsoRepository.findById(id_corso)
                .orElseThrow(() -> new RuntimeException("Corso non trovato" + id_corso));

        corso.getDiscenti().add(discente);
        discente.getCorsi().add(corso);

        return discenteRepository.save(discente);
    }
    public Discente updateDiscente(Discente discente) {
        Discente existingDiscente = discenteRepository.findById(discente.getId())
                .orElseThrow(() -> new RuntimeException("Discente non trovato con id: " + discente.getId()));

        existingDiscente.setNome(discente.getNome());
        existingDiscente.setCognome(discente.getCognome());
        existingDiscente.setCitta_di_residenza(discente.getCitta_di_residenza());
        existingDiscente.setMatricola(discente.getMatricola());
        existingDiscente.setEta(discente.getEta());

        return discenteRepository.save(existingDiscente);
    }

    public List<Corso> getCorsiDiscente (Long id_discente) {
        return discenteRepository.findById(id_discente)
                .map(Discente::getCorsi)
                .orElseThrow(() -> new RuntimeException("Corso non trovato" + id_discente));

    }


    public Discente rimuoviCorso(Long discenteId, Long corsoId) {
        Discente discente = discenteRepository.findById(discenteId)
                .orElseThrow(() -> new RuntimeException("Discente non trovato con id: " + discenteId));

        Corso corso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));

        discente.getCorsi().remove(corso);
        corso.getDiscenti().remove(discente);

        return discenteRepository.save(discente);
    }
>>>>>>> Rest-Controller

        if (discente.getCitta_di_residenza() == null || discente.getCitta_di_residenza().isEmpty()) {
            // Imposta un valore di default se il campo è vuoto
            discente.setCitta_di_residenza("Città sconosciuta");  // Modifica con un valore di default appropriato
        }
        if(discenteDTO.getCorsiId()!=null){
            List<Corso> corsi= corsoRepository.findAllById(discenteDTO.getCorsiId());
            discente.setCorsi(corsi);
        }
        discenteRepository.save(discente);
        return discenteMapper.toDTO(discente);
    }
    public void delete(Long id) {
        discenteRepository.deleteById(id);;
    }

}
