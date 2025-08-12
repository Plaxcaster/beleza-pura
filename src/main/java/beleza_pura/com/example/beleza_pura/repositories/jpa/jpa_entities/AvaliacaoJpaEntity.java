package beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities;

import beleza_pura.com.example.beleza_pura.entities.Avaliacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "AVALIACAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private EstabelecimentoJpaEntity estabelecimento;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalJpaEntity profissional;

    @Column(nullable = false)
    private int nota;

    @Column(length = 500)
    private String comentario;

    public AvaliacaoJpaEntity(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.estabelecimento = new EstabelecimentoJpaEntity(avaliacao.getEstabelecimento());
        this.profissional = new ProfissionalJpaEntity(avaliacao.getProfissional());
        this.nota = avaliacao.getNota();
        this.comentario = avaliacao.getComentario();
    }

    public Avaliacao toAvaliacao() {
        return Avaliacao.builder()
                .id(id)
                .estabelecimento(estabelecimento.toEstabelecimento())
                .profissional(profissional.toProfissionalEntidade())
                .nota(nota)
                .comentario(comentario)
                .build();
    }
}