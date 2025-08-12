package beleza_pura.com.example.beleza_pura.entities;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    private Long id;
    private Cliente cliente;
    private Profissional profissional;
    private Especialidade especialidade;
    private Estabelecimento estabelecimento;  // Added field
    private LocalDateTime dataHora;
    private StatusAgendamento status;
}