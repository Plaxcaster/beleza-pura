
package beleza_pura.com.example.beleza_pura.data;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import lombok.Data;

@Data
public class RespostaConsultaEstabelecimento {
    private String nome;
    private String horarioAbertura;
    private String horarioFechamento;
    private String endereco;

    public RespostaConsultaEstabelecimento(Estabelecimento estabelecimento) {
        this.nome = estabelecimento.getNome();
        this.horarioAbertura = estabelecimento.getHorarioAtendimento().getAbertura().toString();
        this.horarioFechamento = estabelecimento.getHorarioAtendimento().getFechamento().toString();
        this.endereco = estabelecimento.getEndereco();
    }

}
