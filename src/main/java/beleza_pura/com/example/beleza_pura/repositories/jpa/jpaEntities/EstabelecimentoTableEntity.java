package beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.entities.HorarioAtendimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ESTABELECIMENTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoTableEntity {
    @Id
    private UUID id;
    private String nome;
    private String endereco;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    @OneToMany
    @JoinTable(name = "ESTABELECIMENTO_PROFISSIONAL", joinColumns = @JoinColumn(name = "estabelecimento_id"), inverseJoinColumns = @JoinColumn(name = "profissional_id"))
    private Set<ProfissionalTableEntity> profissionais;

    public EstabelecimentoTableEntity(Estabelecimento estabelecimento) {
        this.id = estabelecimento.getId();
        this.nome = estabelecimento.getNome();
        this.endereco = estabelecimento.getEndereco();
        this.horarioInicio = estabelecimento.getHorarioAtendimento().getHorarioInicio();
        this.horarioFim = estabelecimento.getHorarioAtendimento().getHorarioFim();
        Set<ProfissionalTableEntity> profissionaisTable = new HashSet<>();
        estabelecimento.getProfissionais()
                .forEach(profissional -> profissionaisTable.add(new ProfissionalTableEntity(profissional)));
        this.profissionais = profissionaisTable;
    }

    public Estabelecimento toEstabelecimento() {
        Set<Profissional> profissionaisEntity = new HashSet<>();
        this.profissionais.forEach(profissional -> profissionaisEntity.add(profissional.toProfissionalEntidade()));
        HorarioAtendimento horario = new HorarioAtendimento(this.horarioInicio, this.horarioFim);
        return new Estabelecimento(this.id, this.nome, this.endereco, profissionaisEntity, horario);
    }

}
