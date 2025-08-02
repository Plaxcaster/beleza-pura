package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import beleza_pura.com.example.beleza_pura.domain.models.*;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EstabelecimentoMapper.class, DisponibilidadeMapper.class})
public interface ProfissionalMapper {

    @Named("withoutRelations")
    @Mapping(target = "estabelecimento", ignore = true)
    @Mapping(target = "disponibilidade", ignore = true)
    Profissional toDomainWithoutRelations(ProfissionalJpaEntity entity);

    @Named("withoutEstabelecimento")
    @Mapping(target = "estabelecimento", ignore = true)
    @Mapping(target = "disponibilidade", source = "entity", qualifiedByName = "mapDisponibilidade")
    Profissional toDomainWithoutEstabelecimento(ProfissionalJpaEntity entity);

    @Named("withEstabelecimento")
    @Mapping(target = "estabelecimento", source = "estabelecimento", qualifiedByName = "mapEstabelecimento")
    @Mapping(target = "disponibilidade", source = "entity", qualifiedByName = "mapDisponibilidade")
    Profissional toDomain(ProfissionalJpaEntity entity);

    @Named("mapEstabelecimento")
    @Mapping(target = "profissionais", ignore = true)
    Estabelecimento mapEstabelecimento(EstabelecimentoJpaEntity entity);

    @Named("mapDisponibilidade")
    default Disponibilidade mapDisponibilidade(ProfissionalJpaEntity entity) {
        if (entity.getDisponibilidade() == null) {
            return Disponibilidade.createEmpty();
        }
        return Disponibilidade.builder()
                .id(entity.getDisponibilidade().getId())
                .horaInicio(entity.getDisponibilidade().getHoraInicio())
                .horaFim(entity.getDisponibilidade().getHoraFim())
                .valorHora(entity.getDisponibilidade().getValorHora())
                .build();
    }

    default Set<Profissional> toDomainSetWithoutEstabelecimento(Set<ProfissionalJpaEntity> entities) {
        return entities.stream()
                .map(this::toDomainWithoutEstabelecimento)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "disponibilidade", ignore = true) // Handled separately in service layer
    ProfissionalJpaEntity toJpaEntity(Profissional profissional);
}