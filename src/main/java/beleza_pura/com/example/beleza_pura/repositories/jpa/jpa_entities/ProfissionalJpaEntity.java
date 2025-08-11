package beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities;

import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import beleza_pura.com.example.beleza_pura.entities.HorarioAtendimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "PROFISSIONAL")
@Getter
@Setter
@NoArgsConstructor
public class ProfissionalJpaEntity {
    @Id
    private UUID id;
    private String nome;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    @ManyToMany
    @JoinTable(name = "ESPECIALIDADE_PROFISSIONAL", joinColumns = @JoinColumn(name = "profissional_id"), inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
    private Set<EspecialidadeJpaEntity> especialidades;
    @Column(precision = 10, scale = 2) // Adjust precision/scale as needed
    private BigDecimal tarifa;

    public Profissional toProfissionalEntidade() {
        HorarioAtendimento horarioEntidade = new HorarioAtendimento(horarioInicio, horarioFim);
        List<Especialidade> especialidadesEntidade = new LinkedList<>();

        especialidades.forEach(especialidade -> especialidadesEntidade.add(especialidade.toEspecialidadeEntidade()));

        return Profissional.builder().id(id).nome(nome).horario(horarioEntidade)
                .especialidades(especialidadesEntidade).build();
    }

    public ProfissionalJpaEntity(Profissional profissional) {
        this.nome = profissional.getNome();
        this.horarioInicio = profissional.getHorario().getHorarioInicio();
        this.horarioFim = profissional.getHorario().getHorarioFim();
        this.id = profissional.getId();
        this.tarifa = profissional.getTarifa();
        Set<EspecialidadeJpaEntity> especialidadesTable = new HashSet<>();
        profissional.getEspecialidades()
                .forEach(especialidade -> especialidadesTable.add(new EspecialidadeJpaEntity(especialidade)));
        this.especialidades = especialidadesTable;

    }

}
