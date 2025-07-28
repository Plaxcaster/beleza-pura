package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;

import beleza_pura.com.example.beleza_pura.domain.models.Disponibilidade;
import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.DisponibilidadeEmbeddable;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalJpaEntity;

@Mapper(componentModel = "spring")
public interface DisponibilidadeMapper {
    DisponibilidadeEmbeddable toEmbeddable(Disponibilidade domain);
    Disponibilidade toDomain(DisponibilidadeEmbeddable embeddable);
}