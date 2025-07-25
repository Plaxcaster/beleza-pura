// src/main/java/beleza_pura/com/example/beleza_pura/domain/repositories/AgendamentoRepository.java
package beleza_pura.com.example.beleza_pura.domain.repositories;

import beleza_pura.com.example.beleza_pura.domain.models.Agendamento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository {
    Agendamento salvar(Agendamento agendamento);
    Optional<Agendamento> buscarPorId(Long id);
    List<Agendamento> buscarPorProfissional(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
}