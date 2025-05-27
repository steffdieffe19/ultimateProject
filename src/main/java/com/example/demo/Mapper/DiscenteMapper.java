package com.example.demo.Mapper;

import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

    @Mapper(componentModel = "spring")
    public interface DiscenteMapper {

        @Mapping(target = "corsiId", source = "corsi", qualifiedByName = "mapCorsiToIds")
        DiscenteDTO toDTO(Discente discente);

        @Mapping(target = "corsi", ignore = true)
         Discente toEntity(DiscenteDTO dto);

        List<DiscenteDTO> toDTOList(List<Discente> discenti);

        @Named("mapCorsiToIds")
        static List<Long> mapCorsiToIds(List<Corso> corsi) {
            if (corsi == null) return null;
            return corsi.stream().map(Corso::getId).toList();
        }
    }

