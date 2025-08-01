package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "profissionais")
@Getter
@Setter
public class ProfissionalJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ElementCollection
    @CollectionTable(
            name = "profissional_especialidades",
            joinColumns = @JoinColumn(name = "profissional_id")
    )
    @Column(name = "especialidade", length = 50)
    private Set<String> especialidades;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfissionalDisponibilidade> disponibilidades;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private EstabelecimentoJpaEntity estabelecimento;
}