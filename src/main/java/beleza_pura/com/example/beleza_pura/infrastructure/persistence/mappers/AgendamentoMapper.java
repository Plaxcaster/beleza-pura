package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import beleza_pura.com.example.beleza_pura.domain.models.Agendamento;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.AgendamentoJpaEntity;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {
    AgendamentoMapper INSTANCE = Mappers.getMapper(AgendamentoMapper.class);

    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "profissional", target = "profissional")
    @Mapping(source = "servico", target = "servico")
    AgendamentoJpaEntity toJpaEntity(Agendamento domain);
    
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "profissional", target = "profissional")
    @Mapping(source = "servico", target = "servico")
    Agendamento toDomain(AgendamentoJpaEntity jpaEntity);
}