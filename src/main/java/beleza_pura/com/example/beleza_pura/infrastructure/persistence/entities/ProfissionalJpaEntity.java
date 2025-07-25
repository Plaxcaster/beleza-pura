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

    @Column(nullable = false)
    private String nome;

    @ElementCollection
    @CollectionTable(
        name = "profissional_especialidades",
        joinColumns = @JoinColumn(name = "profissional_id")
    )
    @Column(name = "especialidade")
    private Set<String> especialidades;

    @Embedded
    private DisponibilidadeEmbeddable disponibilidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private EstabelecimentoJpaEntity estabelecimento;
}

@Embeddable
@Getter
@Setter
class DisponibilidadeEmbeddable {
    private String diasTrabalho; // Ex: "SEG,QUA,SEX"
    private String horaInicio;   // Ex: "09:00"
    private String horaFim;      // Ex: "18:00"
}