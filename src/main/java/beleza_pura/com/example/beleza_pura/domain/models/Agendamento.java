package beleza_pura.com.example.beleza_pura.domain.models;

import java.time.LocalDateTime;

public class Agendamento {
    private Long id;
    private Cliente cliente;
    private Profissional profissional;
    private Servico servico;
    private LocalDateTime dataHora;
    private StatusAgendamento status;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Profissional getProfissional() { return profissional; }
    public void setProfissional(Profissional profissional) { this.profissional = profissional; }
    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public StatusAgendamento getStatus() { return status; }
    public void setStatus(StatusAgendamento status) { this.status = status; }

    // Domain logic
    public void confirmar() {
        if (this.status != StatusAgendamento.PENDENTE) {
            throw new IllegalStateException("Só pode confirmar agendamentos pendentes");
        }
        this.status = StatusAgendamento.CONFIRMADO;
    }
}