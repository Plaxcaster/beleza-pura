package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;

import beleza_pura.com.example.beleza_pura.domain.models.Cliente;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ClienteJpaEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteJpaEntity toJpaEntity(Cliente domain);
    Cliente toDomain(ClienteJpaEntity jpaEntity);
}