package beleza_pura.com.example.beleza_pura.entities;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Disponibilidade {
    private Set<DayOfWeek> diasTrabalho;
    private LocalTime horaInicio;
    private LocalTime horaFim;
}