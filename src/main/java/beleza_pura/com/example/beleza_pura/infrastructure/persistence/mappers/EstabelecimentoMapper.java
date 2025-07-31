package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.EstabelecimentoJpaEntity;

@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper {
    EstabelecimentoJpaEntity toJpaEntity(Estabelecimento domain);
    Estabelecimento toDomain(EstabelecimentoJpaEntity jpaEntity);
}