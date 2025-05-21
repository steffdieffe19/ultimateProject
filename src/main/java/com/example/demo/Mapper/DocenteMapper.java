package com.example.demo.Mapper;

import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocenteMapper {

    private final ModelMapper modelMapper;

    public DocenteMapper() {
        this.modelMapper = new ModelMapper();
        configureModelMapper();
    }

    private void configureModelMapper() {
        modelMapper.createTypeMap(Docente.class, DocenteDTO.class).addMappings(
                mapper -> mapper.map(src -> mapCorsiToIds(src.getCorsi()), DocenteDTO::setCorsiId));
        modelMapper.createTypeMap(DocenteDTO.class, Docente.class).addMappings(
                mapper -> mapper.map(src -> mapIdsToCorsi(src.getCorsiId()), Docente::setCorsi));
    }
public DocenteDTO toDTO(Docente docente) {
        if (docente == null) {
            return null;
    }
        return modelMapper.map(docente, DocenteDTO.class);
    }

    public Docente toEntity(DocenteDTO docenteDTO) {
        if (docenteDTO == null) {
            return null;
        }
        return modelMapper.map(docenteDTO, Docente.class);
    }
    public List<DocenteDTO> toDTOList(List<Docente> docenti) {
        if (docenti == null) {
            return null;
        }
        return docenti.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Long> mapCorsiToIds(List<Corso> corsi) {
       if (corsi == null) return null;
       return corsi.stream()
                .map(Corso::getId)
                .collect(Collectors.toList());
    }
    public List<Corso> mapIdsToCorsi(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    Corso corso = new Corso();
                    corso.setId(id);
                    return corso;
                })
                .collect(Collectors.toList());
    }


}