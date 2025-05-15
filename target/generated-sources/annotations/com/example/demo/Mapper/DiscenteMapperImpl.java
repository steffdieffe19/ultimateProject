package com.example.demo.Mapper;

import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Discente;
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
public class DiscenteMapperImpl implements DiscenteMapper {

    @Override
    public DiscenteDTO toDTO(Discente discente) {
        if ( discente == null ) {
            return null;
        }

        DiscenteDTO discenteDTO = new DiscenteDTO();

        discenteDTO.setCorsiId( DiscenteMapper.mapCorsiToIds( discente.getCorsi() ) );
        discenteDTO.setId( discente.getId() );
        discenteDTO.setNome( discente.getNome() );
        discenteDTO.setCognome( discente.getCognome() );
        discenteDTO.setCitta_di_residenza( discente.getCitta_di_residenza() );
        discenteDTO.setMatricola( discente.getMatricola() );
        discenteDTO.setEta( discente.getEta() );

        return discenteDTO;
    }

    @Override
    public Discente toEntity(DiscenteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Discente discente = new Discente();

        discente.setId( dto.getId() );
        discente.setNome( dto.getNome() );
        discente.setCognome( dto.getCognome() );
        discente.setCitta_di_residenza( dto.getCitta_di_residenza() );
        discente.setMatricola( dto.getMatricola() );
        discente.setEta( dto.getEta() );

        return discente;
    }

    @Override
    public List<DiscenteDTO> toDTOList(List<Discente> discenti) {
        if ( discenti == null ) {
            return null;
        }

        List<DiscenteDTO> list = new ArrayList<DiscenteDTO>( discenti.size() );
        for ( Discente discente : discenti ) {
            list.add( toDTO( discente ) );
        }

        return list;
    }
}
