// src/main/java/beleza_pura/com/example/beleza_pura/infrastructure/persistence/repositories/AgendamentoRepositoryImpl.java
package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import beleza_pura.com.example.beleza_pura.domain.models.Agendamento;
import beleza_pura.com.example.beleza_pura.domain.repositories.AgendamentoRepository;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.AgendamentoJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers.AgendamentoMapper;

@Repository
public class AgendamentoRepositoryImpl implements AgendamentoRepository {
    private final SpringDataAgendamentoRepository jpaRepository;
    private final AgendamentoMapper mapper;

    public AgendamentoRepositoryImpl(SpringDataAgendamentoRepository jpaRepository, 
                                    AgendamentoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Agendamento salvar(Agendamento agendamento) {
        AgendamentoJpaEntity jpaEntity = mapper.toJpaEntity(agendamento);
        AgendamentoJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Agendamento> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Agendamento> buscarPorProfissional(Long profissionalId, LocalDateTime inicio, LocalDateTime fim) {
        return jpaRepository.findByProfissionalIdAndDataHoraBetween(profissionalId, inicio, fim)
            .stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }
}

interface SpringDataAgendamentoRepository extends JpaRepository<AgendamentoJpaEntity, Long> {
    List<AgendamentoJpaEntity> findByProfissionalIdAndDataHoraBetween(
        Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
}