package beleza_pura.com.example.beleza_pura.entities;

import java.time.LocalDateTime;

public class Agendamento {
    private Long id;
    private Cliente cliente;
    private Profissional profissional;
    private Especialidade especialidade;
    private LocalDateTime dataHora;
    private StatusAgendamento status;
}
