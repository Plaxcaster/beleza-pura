// src/main/java/beleza_pura/com/example/beleza_pura/infrastructure/persistence/entities/AgendamentoJpaEntity.java
package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.*;
import beleza_pura.com.example.beleza_pura.domain.models.StatusAgendamento;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class AgendamentoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteJpaEntity cliente;
    
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalJpaEntity profissional;
    
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoJpaEntity servico;
    
    private LocalDateTime dataHora;
    
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ClienteJpaEntity getCliente() { return cliente; }
    public void setCliente(ClienteJpaEntity cliente) { this.cliente = cliente; }
    public ProfissionalJpaEntity getProfissional() { return profissional; }
    public void setProfissional(ProfissionalJpaEntity profissional) { this.profissional = profissional; }
    public ServicoJpaEntity getServico() { return servico; }
    public void setServico(ServicoJpaEntity servico) { this.servico = servico; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public StatusAgendamento getStatus() { return status; }
    public void setStatus(StatusAgendamento status) { this.status = status; }
}