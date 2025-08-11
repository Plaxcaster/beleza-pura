package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.entities.Agendamento;
import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.entities.StatusAgendamento;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.AgendamentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.ProfissionalRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ConsultaUsecase {


    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final AgendamentoRepository agendamentoRepository;

    public ConsultaUsecase(EstabelecimentoRepository estabelecimentoRepository,
                           ProfissionalRepository profissionalRepository,
                           AgendamentoRepository agendamentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.profissionalRepository = profissionalRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public Estabelecimento consultaEstabelecimentoPorId(String estabelecimentoId) {
        UUID id = converteUUID(estabelecimentoId);
        return estabelecimentoRepository.buscaPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    }

    public Profissional consultaProfissionalPorId(String idProfissional) {
        UUID id = converteUUID(idProfissional);
        return profissionalRepository.buscaPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));
    }

    private UUID converteUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID informada não é válida", e);
        }
    }
    public List<Agendamento> consultarAgendamentosPorCliente(UUID clienteId) {
        return agendamentoRepository.listarPorCliente(clienteId);
    }

    public List<Agendamento> consultarAgendamentosPorProfissional(UUID profissionalId) {
        return agendamentoRepository.listarPorProfissional(profissionalId);
    }

    public List<Agendamento> consultarAgendamentosPorStatus(StatusAgendamento status) {
        return agendamentoRepository.listarPorStatus(status);
    }

    public List<Agendamento> consultarAgendamentosPorClienteEStatus(UUID clienteId, StatusAgendamento status) {
        return agendamentoRepository.listarPorClienteEStatus(clienteId, status);
    }

    public List<Agendamento> consultarAgendamentosPorProfissionalEStatus(UUID profissionalId, StatusAgendamento status) {
        return agendamentoRepository.listarPorProfissionalEStatus(profissionalId, status);
    }
}