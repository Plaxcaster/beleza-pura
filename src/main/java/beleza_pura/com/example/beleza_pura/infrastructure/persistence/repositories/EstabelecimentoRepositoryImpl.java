package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import beleza_pura.com.example.beleza_pura.domain.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.EstabelecimentoJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers.EstabelecimentoMapper;

@Repository
public class EstabelecimentoRepositoryImpl implements EstabelecimentoRepository {

    private final SpringDataEstabelecimentoRepository jpaRepository;
    private final EstabelecimentoMapper mapper;

    public EstabelecimentoRepositoryImpl(SpringDataEstabelecimentoRepository jpaRepository,
                                         EstabelecimentoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Estabelecimento save(Estabelecimento estabelecimento) {
        EstabelecimentoJpaEntity jpaEntity = mapper.toJpaEntity(estabelecimento);
        EstabelecimentoJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Estabelecimento> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}