package beleza_pura.com.example.beleza_pura.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CadastrarProfissionalRequisicao {
    private String nome;
    private String horarioInicio;
    private String horarioFim;
    private BigDecimal tarifa;

}
