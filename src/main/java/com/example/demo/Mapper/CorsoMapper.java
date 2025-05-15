package com.example.demo.Mapper;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CorsoMapper {
    @Mapping(source = "docente.id", target = "id_docente")
    @Mapping(source = "discenti", target = "discentiId", qualifiedByName = "mapDiscentiToIds" )
    CorsoDTO toDto(Corso corso);

    @Mapping(source = "id_docente", target = "docente.id")
    @Mapping(target = "discenti", ignore = true)
    Corso toEntity(CorsoDTO corsoDTO);

    List<CorsoDTO> toDtoList(List<Corso> corsi);

    @Named("mapDiscentiToIds")
    static List<Long> mapDiscentiToIds(List<Discente>discenti){
        if (discenti ==null) return null;
        return discenti.stream().map(Discente::getId).toList();
    }
}
