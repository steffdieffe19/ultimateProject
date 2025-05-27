package com.example.demo.Mapper;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
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
public class CorsoMapperImpl implements CorsoMapper {

    @Override
    public CorsoDTO toDto(Corso corso) {
        if ( corso == null ) {
            return null;
        }

        CorsoDTO corsoDTO = new CorsoDTO();

        corsoDTO.setId_docente( corsoDocenteId( corso ) );
        corsoDTO.setDiscentiId( CorsoMapper.mapDiscentiToIds( corso.getDiscenti() ) );
        corsoDTO.setId( corso.getId() );
        corsoDTO.setNome( corso.getNome() );
        corsoDTO.setAnno_accademico( corso.getAnno_accademico() );

        return corsoDTO;
    }

    @Override
    public Corso toEntity(CorsoDTO corsoDTO) {
        if ( corsoDTO == null ) {
            return null;
        }

        Corso corso = new Corso();

        corso.setDocente( corsoDTOToDocente( corsoDTO ) );
        corso.setId( corsoDTO.getId() );
        corso.setNome( corsoDTO.getNome() );
        corso.setAnno_accademico( corsoDTO.getAnno_accademico() );

        return corso;
    }

    @Override
    public List<CorsoDTO> toDtoList(List<Corso> corsi) {
        if ( corsi == null ) {
            return null;
        }

        List<CorsoDTO> list = new ArrayList<CorsoDTO>( corsi.size() );
        for ( Corso corso : corsi ) {
            list.add( toDto( corso ) );
        }

        return list;
    }

    private Long corsoDocenteId(Corso corso) {
        if ( corso == null ) {
            return null;
        }
        Docente docente = corso.getDocente();
        if ( docente == null ) {
            return null;
        }
        Long id = docente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Docente corsoDTOToDocente(CorsoDTO corsoDTO) {
        if ( corsoDTO == null ) {
            return null;
        }

        Docente docente = new Docente();

        docente.setId( corsoDTO.getId_docente() );

        return docente;
    }
}
