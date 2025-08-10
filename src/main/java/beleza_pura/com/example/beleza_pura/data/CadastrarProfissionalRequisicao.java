package beleza_pura.com.example.beleza_pura.data;

import lombok.Data;

@Data
public class CadastrarProfissionalRequisicao {
    private String nome;
    private String horarioInicio;
    private String horarioFim;
    private int valor;
}
