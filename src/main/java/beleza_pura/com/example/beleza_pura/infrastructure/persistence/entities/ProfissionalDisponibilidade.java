package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profissional_disponibilidades")
@Getter
@Setter
public class ProfissionalDisponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private ProfissionalJpaEntity profissional;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private DayOfWeek dia;

    @Embedded
    private HorarioDisponivelEmbeddable horario;

    @Column(name = "valor_hora")
    private Double valorHora; // Removed precision/scale
}