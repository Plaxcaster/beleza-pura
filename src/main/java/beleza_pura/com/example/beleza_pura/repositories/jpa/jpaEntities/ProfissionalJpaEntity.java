package beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;


import beleza_pura.com.example.beleza_pura.entities.Disponibilidade;
import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalTime horarioFechamento;
    private int valor;

    @ManyToMany
    @JoinTable(name = "ESPECIALIDADE_PROFISSIONAL", joinColumns = @JoinColumn(name = "profissional_id"), inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
    private Set<EspecialidadeJpaEntity> especialidades;

    public Profissional toProfissionalEntidade() {
        Disponibilidade disponibilidadeEntidade = new Disponibilidade(horarioInicio, horarioFechamento);
        Set<Especialidade> especialidadesEntidade = new HashSet<>();

        especialidades.forEach(especialidade -> especialidadesEntidade.add(especialidade.toEspecialidadeEntidade()));

        return Profissional.builder().id(id).nome(nome).disponibilidade(disponibilidadeEntidade)
                .especialidades(especialidadesEntidade).build();
    }

    public ProfissionalJpaEntity(Profissional profissional) {
        this.nome = profissional.getNome();
        this.horarioInicio = profissional.getDisponibilidade().getHoraInicio();
        this.horarioFechamento = profissional.getDisponibilidade().getHoraFim();
        this.id = profissional.getId();
        Set<EspecialidadeJpaEntity> especialidadesTable = new HashSet<>();
        profissional.getEspecialidades()
                .forEach(especialidade -> especialidadesTable.add(new EspecialidadeJpaEntity(especialidade)));
        this.especialidades = especialidadesTable;

    }

}
