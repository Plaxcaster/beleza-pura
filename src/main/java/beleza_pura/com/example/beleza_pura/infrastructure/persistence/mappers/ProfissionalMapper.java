package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;

import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalJpaEntity;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {
    ProfissionalJpaEntity toJpaEntity(Profissional domain);
    Profissional toDomain(ProfissionalJpaEntity jpaEntity);
}