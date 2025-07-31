// src/main/java/beleza_pura/com/example/beleza_pura/infrastructure/persistence/repositories/ServicoRepositoryImpl.java
package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import beleza_pura.com.example.beleza_pura.domain.models.Servico;
import beleza_pura.com.example.beleza_pura.domain.repositories.ServicoRepository;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ServicoJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers.ServicoMapper;

@Repository
public class ServicoRepositoryImpl implements ServicoRepository {

    private final SpringDataServicoRepository jpaRepository;
    private final ServicoMapper mapper;

    public ServicoRepositoryImpl(SpringDataServicoRepository jpaRepository,
                                 ServicoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Servico save(Servico servico) {
        ServicoJpaEntity jpaEntity = mapper.toJpaEntity(servico);
        ServicoJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Servico> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}