package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Agendamento;
import beleza_pura.com.example.beleza_pura.repositories.jpa.AgendamentoJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.AgendamentoJpaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AgendamentoRepository {
    private final AgendamentoJpaRepository repository;

    public AgendamentoRepository(AgendamentoJpaRepository repository) {
        this.repository = repository;
    }

    public Agendamento salvar(Agendamento agendamento) {
        AgendamentoJpaEntity entity = new AgendamentoJpaEntity(agendamento);
        entity = repository.save(entity);
        return entity.toAgendamento();
    }

    public Optional<Agendamento> buscarPorId(Long id) {
        return repository.findById(id)
                .map(AgendamentoJpaEntity::toAgendamento);
    }

    public List<Agendamento> listarTodos() {
        return repository.findAll().stream()
                .map(AgendamentoJpaEntity::toAgendamento)
                .toList();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public boolean existeAgendamentoNoHorario(UUID profissionalId, LocalDateTime dataHora) {
        return repository.existsByProfissionalIdAndDataHora(profissionalId, dataHora);
    }

    public List<Agendamento> listarPorCliente(UUID clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(AgendamentoJpaEntity::toAgendamento)
                .toList();
    }

    public List<Agendamento> listarPorProfissional(UUID profissionalId) {
        return repository.findByProfissionalId(profissionalId).stream()
                .map(AgendamentoJpaEntity::toAgendamento)
                .toList();
    }

}