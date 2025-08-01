package beleza_pura.com.example.beleza_pura.application.dtos;

import java.util.Set;
import java.time.DayOfWeek;
import java.time.LocalTime;

public record NovoProfissionalDTO(
        String nome,
        Set<String> especialidades,
        Long estabelecimentoId,
        Set<DisponibilidadeRequest> disponibilidade,
        Double valorHora
) {
    public record DisponibilidadeRequest(
            DayOfWeek diaSemana,
            LocalTime horaInicio,
            LocalTime horaFim
    ) {}
}