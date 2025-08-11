package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.data.*;
import beleza_pura.com.example.beleza_pura.entities.*;
import beleza_pura.com.example.beleza_pura.repositories.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AgendamentoUsecase {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final EspecialidadeRepository especialidadeRepository;

    public AgendamentoUsecase(AgendamentoRepository agendamentoRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository,
                              EspecialidadeRepository especialidadeRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    public Agendamento marcarAgendamento(MarcarAgendamentoRequisicao requisicao) {
        // Validate and fetch related entities
        Cliente cliente = clienteRepository.buscaPorId(requisicao.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.buscaPorId(requisicao.getProfissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Especialidade especialidade = especialidadeRepository.buscaPorId(requisicao.getEspecialidadeId())
                .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada"));

        // Check professional's availability
        if (!isProfissionalDisponivel(profissional, requisicao.getDataHora())) {
            throw new IllegalStateException("Profissional não disponível no horário solicitado");
        }

        // Create and save appointment
        Agendamento agendamento = Agendamento.builder()
                .cliente(cliente)
                .profissional(profissional)
                .especialidade(especialidade)
                .dataHora(requisicao.getDataHora())
                .status(StatusAgendamento.AGENDADO)
                .build();

        return agendamentoRepository.salvar(agendamento);
    }

    private boolean isProfissionalDisponivel(Profissional profissional, LocalDateTime dataHora) {
        // Check if within professional's working hours
        if (!profissional.getHorario().estaDentroDoHorario(dataHora.toLocalTime())) {
            return false;
        }

        // Check if professional already has an appointment at this time
        return !agendamentoRepository.existeAgendamentoNoHorario(
                profissional.getId(),
                dataHora
        );
    }

    public Optional<Agendamento> buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.buscarPorId(id);
    }

    public List<Agendamento> listarAgendamentosPorCliente(UUID clienteId) {
        return agendamentoRepository.listarPorCliente(clienteId);
    }

    public List<Agendamento> listarAgendamentosPorProfissional(UUID profissionalId) {
        return agendamentoRepository.listarPorProfissional(profissionalId);
    }

    public Agendamento atualizarStatusAgendamento(Long agendamentoId, StatusAgendamento novoStatus) {
        Agendamento agendamento = agendamentoRepository.buscarPorId(agendamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));

        agendamento.setStatus(novoStatus);
        return agendamentoRepository.salvar(agendamento);
    }
}