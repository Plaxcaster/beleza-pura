package beleza_pura.com.example.beleza_pura.application.services;

import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import beleza_pura.com.example.beleza_pura.application.dtos.ProfissionalDisponibilidadeDTO;
import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.domain.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.domain.repositories.ProfissionalRepository;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DisponibilidadeService {

    private final ProfissionalRepository profissionalRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public DisponibilidadeService(ProfissionalRepository profissionalRepository,
                                  EstabelecimentoRepository estabelecimentoRepository) {
        this.profissionalRepository = profissionalRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public List<ProfissionalDisponibilidadeDTO> getDisponibilidadeProfissionais(
            Long estabelecimentoId, LocalDate data) {

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));

        DayOfWeek diaSemana = data.getDayOfWeek();
        List<Profissional> profissionais = profissionalRepository.findByEstabelecimentoId(estabelecimentoId);

        return profissionais.stream()
                .map(profissional -> mapToDTO(profissional, diaSemana, estabelecimento))
                .collect(Collectors.toList());
    }

    private ProfissionalDisponibilidadeDTO mapToDTO(Profissional profissional, DayOfWeek diaSemana, Estabelecimento estabelecimento) {
        return new ProfissionalDisponibilidadeDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEspecialidades(),
                getDisponibilidadeSlots(profissional, diaSemana),
                getEstabelecimentoHorario(profissional)
        );
    }

    private Set<ProfissionalDisponibilidadeDTO.ServicoDisponivelDTO> getServicosDisponiveis(
            Estabelecimento estabelecimento) {
        // Fetch services available at the establishment
        return estabelecimento.getServicos().stream()
                .map(servico -> new ProfissionalDisponibilidadeDTO.ServicoDisponivelDTO(
                        servico.getId(),
                        servico.getNome(),
                        (int) servico.getDuracaoMinutos()
                ))
                .collect(Collectors.toSet());
    }

    private Set<ProfissionalDisponibilidadeDTO.DisponibilidadeSlotDTO> getDisponibilidadeSlots(
            Profissional profissional, DayOfWeek diaSemana) {
        // Simplified logic - in real implementation, this would:
        // 1. Get professional's availability for the day
        // 2. Subtract booked appointments
        return Set.of(
                new ProfissionalDisponibilidadeDTO.DisponibilidadeSlotDTO(
                        LocalTime.parse(profissional.getDisponibilidade().getHoraInicio()),
                        LocalTime.parse(profissional.getDisponibilidade().getHoraFim())
                )
        );
    }

    private ProfissionalDisponibilidadeDTO.EstabelecimentoHorarioDTO getEstabelecimentoHorario(
            Profissional profissional) {
        return new ProfissionalDisponibilidadeDTO.EstabelecimentoHorarioDTO(
                profissional.getEstabelecimento().getHorarioFuncionamento().getAbertura(),
                profissional.getEstabelecimento().getHorarioFuncionamento().getFechamento()
        );
    }
}