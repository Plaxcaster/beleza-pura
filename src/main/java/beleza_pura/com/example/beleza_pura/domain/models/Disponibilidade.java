package beleza_pura.com.example.beleza_pura.domain.models;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Disponibilidade {
    private String diasTrabalho;
    private String horaInicio;
    private String horaFim;


    public Disponibilidade() {

    }
}