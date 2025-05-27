package com.example.demo.Mapper;

import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Docente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-15T21:36:08+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Eclipse Adoptium)"
)
@Component
public class DocenteMapperImpl implements DocenteMapper {

    @Override
    public DocenteDTO toDTO(Docente docente) {
        if ( docente == null ) {
            return null;
        }

        DocenteDTO docenteDTO = new DocenteDTO();

        docenteDTO.setCorsiId( mapCorsiToIds( docente.getCorsi() ) );
        docenteDTO.setId( docente.getId() );
        docenteDTO.setNome( docente.getNome() );
        docenteDTO.setCognome( docente.getCognome() );
        docenteDTO.setData_di_nascita( docente.getData_di_nascita() );

        return docenteDTO;
    }

    @Override
    public Docente toEntity(DocenteDTO docenteDTO) {
        if ( docenteDTO == null ) {
            return null;
        }

        Docente docente = new Docente();

        docente.setCorsi( mapIdsToCorsi( docenteDTO.getCorsiId() ) );
        docente.setId( docenteDTO.getId() );
        docente.setNome( docenteDTO.getNome() );
        docente.setCognome( docenteDTO.getCognome() );
        docente.setData_di_nascita( docenteDTO.getData_di_nascita() );

        return docente;
    }

    @Override
    public List<DocenteDTO> toDTOList(List<Docente> docenti) {
        if ( docenti == null ) {
            return null;
        }

        List<DocenteDTO> list = new ArrayList<DocenteDTO>( docenti.size() );
        for ( Docente docente : docenti ) {
            list.add( toDTO( docente ) );
        }

        return list;
    }
}
