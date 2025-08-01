package beleza_pura.com.example.beleza_pura.domain.models;

import lombok.Data;
import java.util.Set;

@Data
public class Estabelecimento {
    private Long id;
    private String nome;
    private String endereco;
    private Set<String> fotos;
    private HorarioFuncionamento horarioFuncionamento; // Updated type

    // Relationships (not included in initial registration)
    private Set<Servico> servicos;
    private Set<Profissional> profissionais;

    public boolean adicionarServico(Servico servico) {
        return servicos.add(servico);
    }
}