package beleza_pura.com.example.beleza_pura.repositories.jpa;

import beleza_pura.com.example.beleza_pura.entities.StatusAgendamento;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities.AgendamentoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AgendamentoJpaRepository extends JpaRepository<AgendamentoJpaEntity, Long> {
    boolean existsByProfissionalIdAndDataHora(UUID profissionalId, LocalDateTime dataHora);

    List<AgendamentoJpaEntity> findByClienteId(UUID clienteId);

    List<AgendamentoJpaEntity> findByProfissionalId(UUID profissionalId);

    List<AgendamentoJpaEntity> findByStatus(StatusAgendamento status);

    @Query("SELECT a FROM AgendamentoJpaEntity a WHERE a.cliente.id = :clienteId AND a.status = :status")
    List<AgendamentoJpaEntity> findByClienteIdAndStatus(@Param("clienteId") UUID clienteId,
                                                        @Param("status") StatusAgendamento status);

    @Query("SELECT a FROM AgendamentoJpaEntity a WHERE a.profissional.id = :profissionalId AND a.status = :status")
    List<AgendamentoJpaEntity> findByProfissionalIdAndStatus(@Param("profissionalId") UUID profissionalId,
                                                             @Param("status") StatusAgendamento status);
}