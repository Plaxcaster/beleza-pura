package beleza_pura.com.example.beleza_pura.domain.models;



import java.util.Set;

import lombok.Data;

@Data
public class Estabelecimento {
    private Long id;
    private String nome;
    private Endereco endereco;
    private Set<String> fotos;
    private Set<Servico> servicos;
    private Set<Profissional> profissionais;
    private HorarioFuncionamento horarioFuncionamento;

    // Domain logic
    public boolean adicionarServico(Servico servico) {
        return servicos.add(servico);
    }
}
