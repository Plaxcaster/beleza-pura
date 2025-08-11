
package beleza_pura.com.example.beleza_pura.data;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import lombok.Data;

@Data
public class RespostaConsultaEstabelecimento {
    private String nome;
    private String horarioInicio;
    private String horarioFim;
    private String endereco;

    public RespostaConsultaEstabelecimento(Estabelecimento estabelecimento) {
        this.nome = estabelecimento.getNome();
        this.horarioInicio = estabelecimento.getHorarioAtendimento().getHorarioInicio().toString();
        this.horarioFim = estabelecimento.getHorarioAtendimento().getHorarioFim().toString();
        this.endereco = estabelecimento.getEndereco();
    }

}
