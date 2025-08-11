package beleza_pura.com.example.beleza_pura.data;

import beleza_pura.com.example.beleza_pura.entities.Agendamento;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RespostaConsultaAgendamento {
    private Long id;
    private UUID clienteId;
    private String clienteNome;
    private UUID profissionalId;
    private String profissionalNome;
    private UUID especialidadeId;
    private String especialidadeNome;
    private LocalDateTime dataHora;
    private String status;

    public RespostaConsultaAgendamento(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.clienteId = agendamento.getCliente().getId();
        this.clienteNome = agendamento.getCliente().getNome();
        this.profissionalId = agendamento.getProfissional().getId();
        this.profissionalNome = agendamento.getProfissional().getNome();
        this.especialidadeId = agendamento.getEspecialidade().getId();
        this.especialidadeNome = agendamento.getEspecialidade().getNome();
        this.dataHora = agendamento.getDataHora();
        this.status = agendamento.getStatus().toString();
    }
}