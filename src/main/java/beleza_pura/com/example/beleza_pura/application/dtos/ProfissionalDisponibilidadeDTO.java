package beleza_pura.com.example.beleza_pura.application.dtos;

import java.time.LocalTime;
import java.util.Set;

public record ProfissionalDisponibilidadeDTO(
        Long profissionalId,
        String nome,
        Set<String> especialidades, // Keep specialties as strings
        // Renamed for clarity
        Set<DisponibilidadeSlotDTO> disponibilidade,
        EstabelecimentoHorarioDTO horarioFuncionamento
) {
    public record ServicoDisponivelDTO(
            Long id,
            String nome,
            Integer duracaoMinutos
    ) {}

    public record DisponibilidadeSlotDTO(
            LocalTime inicio,
            LocalTime fim
    ) {}

    public record EstabelecimentoHorarioDTO(
            String abertura,
            String fechamento
    ) {}
}