package beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class EstabelecimentoJpaEntity {
    @Id
    private UUID id;
    private String nome;
    private String endereco;
    @OneToMany
    @JoinTable(name = "ESTABELECIMENTO_PROFISSIONAL", joinColumns = @JoinColumn(name = "estabelecimento_id"), inverseJoinColumns = @JoinColumn(name = "profissional_id"))
    private Set<ProfissionalJpaEntity> profissionais;

    public EstabelecimentoTableEntity(Estabelecimento estabelecimento) {
        this.id = estabelecimento.getId();
        this.nome = estabelecimento.getNome();
        this.endereco = estabelecimento.getEndereco();
    }

    public Estabelecimento toEstabelecimento() {

        this.profissionais.forEach(profissional -> profi.add(profissional.toProfissionalEntidade()));
        return new Estabelecimento(this.id, this.nome, this.endereco, profissionaisEntity, horario);
    }

}
