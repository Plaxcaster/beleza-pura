package beleza_pura.com.example.beleza_pura.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HorarioAtendimento {

    private LocalTime horarioInicio;
    private LocalTime horarioFim;
}
