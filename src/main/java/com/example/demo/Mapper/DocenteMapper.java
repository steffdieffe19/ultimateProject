package com.example.demo.Mapper;

import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    @Mapping(target = "corsiId", source = "corsi", qualifiedByName = "mapCorsiToIds")
    DocenteDTO toDTO(Docente docente);

    @Mapping(target = "corsi", source = "corsiId", qualifiedByName = "mapIdsToCorsi")
    Docente toEntity(DocenteDTO docenteDTO);

    @Named("mapCorsiToIds")
    default List<Long> mapCorsiToIds(List<Corso> corsi) {
        if (corsi == null) return null;
        return corsi.stream().map(Corso::getId).collect(Collectors.toList());
    }

    List<DocenteDTO> toDTOList(List<Docente> docenti);

    @Named("mapIdsToCorsi")
    default List<Corso> mapIdsToCorsi(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Corso corso = new Corso();
            corso.setId(id);
            return corso;
        }).collect(Collectors.toList());
    }
}