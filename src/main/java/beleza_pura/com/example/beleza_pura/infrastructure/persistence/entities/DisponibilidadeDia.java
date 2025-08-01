package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.time.DayOfWeek;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DisponibilidadeDia {
    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private DayOfWeek dia;

    @ElementCollection
    @CollectionTable(
            name = "profissional_horarios",
            joinColumns = {
                    @JoinColumn(name = "profissional_id", referencedColumnName = "profissional_id"),
                    @JoinColumn(name = "dia_semana", referencedColumnName = "dia_semana")
            }
    )
    private Set<HorarioDisponivelEmbeddable> horarios;
}