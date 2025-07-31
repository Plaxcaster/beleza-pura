package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.AgendamentoJpaEntity;

public interface SpringDataAgendamentoRepository extends JpaRepository<AgendamentoJpaEntity, Long> {

    @Query("SELECT a FROM AgendamentoJpaEntity a " +
            "WHERE a.profissional.id = :profissionalId " +
            "AND ((a.dataHora BETWEEN :inicio AND :fim) " +
            "OR (a.dataHora <= :inicio AND FUNCTION('TIMESTAMPADD', MINUTE, a.servico.duracaoMinutos, a.dataHora) > :inicio) " +
            "OR (a.dataHora < :fim AND FUNCTION('TIMESTAMPADD', MINUTE, a.servico.duracaoMinutos, a.dataHora) >= :fim))")
    List<AgendamentoJpaEntity> findOverlappingAgendamentos(
            @Param("profissionalId") Long profissionalId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}