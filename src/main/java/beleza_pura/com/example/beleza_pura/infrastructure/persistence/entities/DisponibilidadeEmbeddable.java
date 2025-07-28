package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DisponibilidadeEmbeddable {
    private String diasTrabalho; // Ex: "SEG,QUA,SEX"
    private String horaInicio;   // Ex: "09:00"
    private String horaFim;      // Ex: "18:00"
}