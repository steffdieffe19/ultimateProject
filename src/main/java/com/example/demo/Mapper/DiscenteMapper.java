package com.example.demo.Mapper;

import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.convention.MatchingStrategies;

@Component
public class DiscenteMapper {
    private final ModelMapper modelMapper;

    public DiscenteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureModelMapper();
    }

    private void configureModelMapper() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        modelMapper.typeMap(Discente.class, DiscenteDTO.class)
                .addMappings(mapper -> {
                    // Mapping diretto per i campi semplici
                    mapper.map(Discente::getId, DiscenteDTO::setId);
                    // Mapping personalizzato per i corsi
                    mapper.using(ctx -> {
                        List<Corso> corsi = (List<Corso>) ctx.getSource();
                        if (corsi == null) {
                            return new ArrayList<Long>();
                        }
                        return corsi.stream()
                                .map(Corso::getId)
                                .collect(Collectors.toList());
                    }).map(Discente::getCorsi, DiscenteDTO::setCorsiIds);
                });

        modelMapper.typeMap(DiscenteDTO.class, Discente.class)
                .addMappings(mapper -> {
                    mapper.map(DiscenteDTO::getId, Discente::setId);
                    // Non mappiamo qui i corsi perché dovrebbero essere gestiti separatamente
                    mapper.skip(Discente::setCorsi);
                });
    }

    public DiscenteDTO toDTO(Discente entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, DiscenteDTO.class);
    }

    public Discente toEntity(DiscenteDTO dto) {
        if (dto == null) {
            return null;
        }
        return modelMapper.map(dto, Discente.class);
    }

    public List<DiscenteDTO> toDTOList(List<Discente> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}