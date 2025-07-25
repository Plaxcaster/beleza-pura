package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import beleza_pura.com.example.beleza_pura.domain.models.Agendamento;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.AgendamentoJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ClienteJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ServicoJpaEntity;

@Mapper(componentModel = "spring", 
        uses = {ClienteMapper.class, ProfissionalMapper.class, ServicoMapper.class})
public interface AgendamentoMapper {

    AgendamentoMapper INSTANCE = Mappers.getMapper(AgendamentoMapper.class);

    @Mapping(target = "id", ignore = true) // Let JPA handle ID generation
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "profissional", target = "profissional")
    @Mapping(source = "servico", target = "servico")
    AgendamentoJpaEntity toJpaEntity(Agendamento domain);
    
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "profissional", target = "profissional")
    @Mapping(source = "servico", target = "servico")
    Agendamento toDomain(AgendamentoJpaEntity jpaEntity);
}