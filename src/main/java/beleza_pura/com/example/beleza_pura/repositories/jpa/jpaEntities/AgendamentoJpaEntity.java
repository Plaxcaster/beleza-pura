package beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities;

import beleza_pura.com.example.beleza_pura.entities.Agendamento;
import beleza_pura.com.example.beleza_pura.entities.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "AGENDAMENTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinColumn(name = "especialidade_id")
    private EspecialidadeJpaEntity especialidade;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    public AgendamentoJpaEntity(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.cliente = new ClienteJpaEntity(agendamento.getCliente());
        this.profissional = new ProfissionalJpaEntity(agendamento.getProfissional());
        this.especialidade = new EspecialidadeJpaEntity(agendamento.getEspecialidade());
        this.dataHora = agendamento.getDataHora();
        this.status = agendamento.getStatus();
    }

    public Agendamento toAgendamento() {
        return Agendamento.builder()
                .id(id)
                .cliente(cliente.toClienteEntidade())
                .profissional(profissional.toProfissionalEntidade())
                .especialidade(especialidade.toEspecialidadeEntidade())
                .dataHora(dataHora)
                .status(status)
                .build();
    }
}