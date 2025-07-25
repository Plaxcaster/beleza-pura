package beleza_pura.com.example.beleza_pura.domain.models;

import java.util.Set;

import lombok.Data;

@Data
public class Profissional {
    private Long id;
    private String nome;
    private Set<String> especialidades;
    private Estabelecimento estabelecimento;
    private Disponibilidade disponibilidade;

    // Domain logic
    public boolean possuiEspecialidade(String especialidade) {
        return especialidades.contains(especialidade);
    }

    Object getDisponibilidade() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}