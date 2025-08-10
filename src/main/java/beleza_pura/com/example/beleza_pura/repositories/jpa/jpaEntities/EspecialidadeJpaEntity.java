package beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities;

import java.util.Set;
import java.util.UUID;


import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String descricao;
    @ManyToMany(mappedBy = "especialidades")
    private Set<EspecialidadeJpaEntity> profissionais;

    public EspecialidadeJpaEntity(Especialidade especialidade) {
        this.id = especialidade.getId();
        this.nome = especialidade.getNome();
    }

    public Especialidade toEspecialidadeEntidade() {
        return new Especialidade(id, nome);
    }
}
