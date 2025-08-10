package beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities;

import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import beleza_pura.com.example.beleza_pura.entities.HorarioAtendimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "PROFISSIONAL")
@Getter
@Setter
@NoArgsConstructor
public class ProfissionalTableEntity {
    @Id
    private UUID id;
    private String nome;
    private LocalTime horarioInicio;
    private LocalTime horarioFechamento;
    private int valor;
    @ManyToMany
    @JoinTable(name = "ESPECIALIDADE_PROFISSIONAL", joinColumns = @JoinColumn(name = "profissional_id"), inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
    private Set<EspecialidadeTableEntity> especialidades;

    public Profissional toProfissionalEntidade() {
        HorarioAtendimento horarioEntidade = new HorarioAtendimento(horarioInicio, horarioFechamento);
        List<Especialidade> especialidadesEntidade = new LinkedList<>();

        especialidades.forEach(especialidade -> especialidadesEntidade.add(especialidade.toEspecialidadeEntidade()));

        return Profissional.builder().id(id).nome(nome).horario(horarioEntidade)
                .especialidades(especialidadesEntidade).build();
    }

    public ProfissionalTableEntity(Profissional profissional) {
        this.nome = profissional.getNome();
        this.horarioInicio = profissional.getHorario().getAbertura();
        this.horarioFechamento = profissional.getHorario().getFechamento();
        this.id = profissional.getId();
        Set<EspecialidadeTableEntity> especialidadesTable = new HashSet<>();
        profissional.getEspecialidades()
                .forEach(especialidade -> especialidadesTable.add(new EspecialidadeTableEntity(especialidade)));
        this.especialidades = especialidadesTable;

    }

}
