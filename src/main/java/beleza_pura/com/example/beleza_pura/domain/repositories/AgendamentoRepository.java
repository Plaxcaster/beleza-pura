package beleza_pura.com.example.beleza_pura.domain.repositories;

import beleza_pura.com.example.beleza_pura.domain.models.Agendamento;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository {
    Agendamento save(Agendamento agendamento);
    Optional<Agendamento> findById(Long id);
    List<Agendamento> findOverlappingAgendamentos(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
}