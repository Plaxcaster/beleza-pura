package beleza_pura.com.example.beleza_pura.domain.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Servico {
    private Long id;
    private String nome;
    private String descricao;
    private Integer duracaoMinutos;
    private BigDecimal preco;
    private Estabelecimento estabelecimento;
    private Profissional profissional;

    // Domain logic
    public boolean isDisponivelParaAgendamento() {
        return profissional != null && profissional.getDisponibilidade() != null;
    }

    public boolean possuiMesmoEstabelecimento(Estabelecimento outroEstabelecimento) {
        return estabelecimento != null && estabelecimento.equals(outroEstabelecimento);
    }
}