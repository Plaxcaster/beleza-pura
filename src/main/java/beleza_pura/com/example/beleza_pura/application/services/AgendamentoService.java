// src/main/java/beleza_pura/com/example/beleza_pura/application/services/AgendamentoService.java
package beleza_pura.com.example.beleza_pura.application.services;

import beleza_pura.com.example.beleza_pura.application.dtos.NovoAgendamentoDTO;
import beleza_pura.com.example.beleza_pura.domain.models.Agendamento;
import beleza_pura.com.example.beleza_pura.domain.models.Cliente;
import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.domain.models.Servico;
import beleza_pura.com.example.beleza_pura.domain.models.StatusAgendamento;
import beleza_pura.com.example.beleza_pura.domain.repositories.AgendamentoRepository;
import beleza_pura.com.example.beleza_pura.domain.repositories.ProfissionalRepository;
import beleza_pura.com.example.beleza_pura.domain.repositories.ServicoRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepo;
    private final ProfissionalRepository profissionalRepo;
    private final ServicoRepository servicoRepo;

    public AgendamentoService(AgendamentoRepository agendamentoRepo,
                             ProfissionalRepository profissionalRepo,
                             ServicoRepository servicoRepo) {
        this.agendamentoRepo = agendamentoRepo;
        this.profissionalRepo = profissionalRepo;
        this.servicoRepo = servicoRepo;
    }

    public Agendamento criarAgendamento(NovoAgendamentoDTO dto) {
        Profissional profissional = profissionalRepo.findById(dto.profissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Servico servico = servicoRepo.findById(dto.servicoId())
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        LocalDateTime fim = dto.dataHora().plusMinutes(servico.getDuracaoMinutos());
        boolean disponivel = agendamentoRepo
                .findOverlappingAgendamentos(dto.profissionalId(), dto.dataHora(), fim)
                .isEmpty();

        if (!disponivel) {
            throw new IllegalStateException("Horário indisponível");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(new Cliente(dto.clienteId(), null, null, null));
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHora(dto.dataHora());
        agendamento.setStatus(StatusAgendamento.PENDENTE);

        return agendamentoRepo.save(agendamento);
    }
}