package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.domain.repositories.ProfissionalRepository;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers.ProfissionalMapper;

@Repository
public class ProfissionalRepositoryImpl implements ProfissionalRepository {

    private final SpringDataProfissionalRepository jpaRepository;
    private final ProfissionalMapper mapper;

    public ProfissionalRepositoryImpl(SpringDataProfissionalRepository jpaRepository,
                                      ProfissionalMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Profissional save(Profissional profissional) {
        ProfissionalJpaEntity jpaEntity = mapper.toJpaEntity(profissional);
        ProfissionalJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Profissional> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}