package com.example.demo.Mapper;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CorsoMapper {

    public CorsoDTO toDTO(Corso entity) {
        if (entity == null) {
            return null;
        }

        CorsoDTO dto = new CorsoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setAnno_accademico(entity.getAnno_accademico());
        
        if (entity.getDocente() != null) {
            dto.setDocenteId(entity.getDocente().getId());
        }
        
        if (entity.getDiscenti() != null) {
            List<Long> discentiIds = entity.getDiscenti().stream()
                    .map(Discente::getId)
                    .collect(Collectors.toList());
            dto.setDiscentiIds(discentiIds);
        } else {
            dto.setDiscentiIds(new ArrayList<>());
        }
        
        return dto;
    }

    public Corso toEntity(CorsoDTO dto) {
        if (dto == null) {
            return null;
        }

        Corso entity = new Corso();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setAnno_accademico(dto.getAnno_accademico());
        
        if (dto.getDocenteId() != null) {
            Docente docente = new Docente();
            docente.setId(dto.getDocenteId());
            entity.setDocente(docente);
        }
        
        if (dto.getDiscentiIds() != null) {
            List<Discente> discenti = dto.getDiscentiIds().stream()
                    .map(id -> {
                        Discente discente = new Discente();
                        discente.setId(id);
                        return discente;
                    })
                    .collect(Collectors.toList());
            entity.setDiscenti(discenti);
        } else {
            entity.setDiscenti(new ArrayList<>());
        }
        
        return entity;
    }

    public List<CorsoDTO> toDTOList(List<Corso> entityList) {
        if (entityList == null) {
            return new ArrayList<>();
        }
        return entityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Corso> toEntityList(List<CorsoDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}