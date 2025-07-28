package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;

import beleza_pura.com.example.beleza_pura.domain.models.Servico;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ServicoJpaEntity;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    ServicoJpaEntity toJpaEntity(Servico domain);
    Servico toDomain(ServicoJpaEntity jpaEntity);
}