package com.example.demo.controller;

<<<<<<< HEAD

import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
=======
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.DTO.DiscenteLiteDTO;
import com.example.demo.data.DTO.DocenteLiteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
>>>>>>> Rest-Controller
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< HEAD
import java.util.ArrayList;
import java.sql.SQLException;
=======
import java.util.Optional;
>>>>>>> Rest-Controller


@RestController
@RequestMapping("/api/corsi")
@CrossOrigin(origins = "*")
public class CorsoController {

    private final CorsoService corsoService;

    @Autowired
    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @GetMapping
    public List<CorsoDTO> getAllCorsi() {
        return corsoService.getAllCorsi()
                .stream()
                .map(this::toDTO)
                .toList();
    }

<<<<<<< HEAD
    // FORM NUOVO
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-corso");
        mav.addObject("corso", new Corso());
        mav.addObject("docenti",corsoService.getAllDocenti());
        mav.addObject("tuttiDiscenti", discenteRepository.findAll());
        return mav;
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("corso") Corso corso,
                         @RequestParam(value = "discenteIds", required = false) List<Long> discenteIds,
                         BindingResult br) {
        if (br.hasErrors()) return "form-corso";

        List<Discente> discenti = discenteIds != null
                ? discenteRepository.findAllById(discenteIds)
                : new ArrayList<>();

        corso.setDiscenti(discenti);
        corsoService.save(corso);
        return "redirect:/corso";
=======
    private CorsoDTO toDTO(Corso corso) {
        CorsoDTO dto = new CorsoDTO();
        dto.setNome(corso.getNome());
        dto.setAnno_accademico(corso.getAnno_accademico());

        // Mappa docente
        if (corso.getDocente() != null) {
            Docente docente = corso.getDocente();
            dto.setDocente(new DocenteLiteDTO(docente.getNome(), docente.getCognome()));
        }

        List<DiscenteLiteDTO> discentiDTO = corso.getDiscenti().stream()
                .map(d -> new DiscenteLiteDTO(d.getNome(), d.getCognome()))
                .toList();
        dto.setDiscenti(discentiDTO);

        return dto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Corso> getCorsoById(@PathVariable Long id) {
        return corsoService.getCorsoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
>>>>>>> Rest-Controller
    }

    @PostMapping("/nuovo")
    public ResponseEntity<CorsoDTO> createCorso(@RequestBody Corso corso) {
        Corso saved = corsoService.createCorso(corso);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorsoDTO> updateCorso(
            @PathVariable Long id,
            @RequestBody Corso updatedCorso) {
        Optional<Corso> existing = corsoService.getCorsoById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Corso corso = existing.get();
        corso.setNome(updatedCorso.getNome());
        corso.setAnno_accademico(updatedCorso.getAnno_accademico());

        Docente docentePersistente = corsoService
                .getDocenteByNomeAndCognome(
                        updatedCorso.getDocente().getNome(),
                        updatedCorso.getDocente().getCognome()
                )
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));

        corso.setDocente(docentePersistente);

        Corso saved = corsoService.updateCorso(corso);
        return ResponseEntity.ok(toDTO(saved));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCorso(@PathVariable Long id) {
        corsoService.deleteCorso(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{corsoId}/discenti/{discenteId}")
    public ResponseEntity<Corso> aggiungiDiscenteByNomeCognome(
            @PathVariable Long corsoId,
            @RequestParam String nome,
            @RequestParam String cognome) {
        return ResponseEntity.ok(corsoService.aggiungiDiscente(corsoId, nome, cognome));
    }

    @DeleteMapping("/{corsoId}/discenti/{discenteId}")
    public ResponseEntity<Corso> rimuoviDiscente(
            @PathVariable Long corsoId,
            @RequestParam String nome,
            @RequestParam String cognome) {
        return ResponseEntity.ok(corsoService.rimuoviDiscente(corsoId, nome, cognome));
    }

    @GetMapping("/{corsoId}/discenti")
    public ResponseEntity<List<DiscenteLiteDTO>> getDiscentiCorso(@PathVariable Long corsoId) {
        List<DiscenteLiteDTO> discenti = corsoService.getDiscentiCorso(corsoId).stream()
                .map(d -> new DiscenteLiteDTO(d.getNome(), d.getCognome()))
                .toList();
        return ResponseEntity.ok(discenti);
    }
}
