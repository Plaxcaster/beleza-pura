package beleza_pura.com.example.beleza_pura.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profissional {

    private UUID id;
    private String nome;
    private List<Especialidade> especialidades;
    private HorarioAtendimento horario;
    private BigDecimal tarifa;


    public Profissional(String nome, List<Especialidade> especialidades, HorarioAtendimento horario) {
        this.id = UUID.randomUUID();
        this.especialidades = especialidades;
        this.nome = nome;
        this.horario = horario;
    }
}
