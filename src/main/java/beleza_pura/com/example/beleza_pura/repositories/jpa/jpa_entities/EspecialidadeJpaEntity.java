package beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities;

import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ESPECIALIDADE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadeJpaEntity {
    @Id
    private UUID id;
    private String nome;
    @ManyToMany(mappedBy = "especialidades")
    private Set<ProfissionalJpaEntity> profissionais;

    public EspecialidadeJpaEntity(Especialidade especialidade) {
        this.id = especialidade.getId();
        this.nome = especialidade.getNome();
    }

    public Especialidade toEspecialidadeEntidade() {
        return new Especialidade(id, nome);
    }
}
