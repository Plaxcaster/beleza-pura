package beleza_pura.com.example.beleza_pura.application.dtos;

import java.util.Set;
import java.time.DayOfWeek;
import java.time.LocalTime;

public record ProfissionalDTO(
        Long id,
        String nome,
        Set<String> especialidades,
        Long estabelecimentoId,
        Set<DisponibilidadeResponse> disponibilidades,
        Double valorHora
) {
    public record DisponibilidadeResponse(
            DayOfWeek diaSemana,
            LocalTime horaInicio,
            LocalTime horaFim
    ) {}
}