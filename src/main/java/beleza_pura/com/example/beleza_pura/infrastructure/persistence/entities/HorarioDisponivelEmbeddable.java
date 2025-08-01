package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class HorarioDisponivelEmbeddable {
    @Column(name = "hora_inicio")
    private LocalTime inicio;

    @Column(name = "hora_fim")
    private LocalTime fim;
}