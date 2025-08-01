package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "estabelecimentos")
@Getter
@Setter
public class EstabelecimentoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 200) // Simplified address as single string
    private String endereco;

    @ElementCollection
    @CollectionTable(
            name = "estabelecimento_fotos",
            joinColumns = @JoinColumn(name = "estabelecimento_id")
    )
    @Column(name = "foto_url")
    private Set<String> fotos;

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private Set<ServicoJpaEntity> servicos;

    @OneToMany(mappedBy = "estabelecimento")
    private Set<ProfissionalJpaEntity> profissionais;

    @Embedded
    private HorarioFuncionamentoEmbeddable horarioFuncionamento;
}