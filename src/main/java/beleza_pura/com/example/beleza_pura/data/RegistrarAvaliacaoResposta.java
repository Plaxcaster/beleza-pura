package beleza_pura.com.example.beleza_pura.data;

import beleza_pura.com.example.beleza_pura.entities.Avaliacao;
import lombok.Data;

import java.util.UUID;

@Data
public class RegistrarAvaliacaoResposta {
    private UUID id;
    private UUID estabelecimentoId;
    private String estabelecimentoNome;
    private UUID profissionalId;
    private String profissionalNome;
    private int nota;
    private String comentario;

    public RegistrarAvaliacaoResposta(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.estabelecimentoId = avaliacao.getEstabelecimento().getId();
        this.estabelecimentoNome = avaliacao.getEstabelecimento().getNome();
        this.profissionalId = avaliacao.getProfissional().getId();
        this.profissionalNome = avaliacao.getProfissional().getNome();
        this.nota = avaliacao.getNota();
        this.comentario = avaliacao.getComentario();
    }
}