package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.data.RegistrarAvaliacaoRequisicao;
import beleza_pura.com.example.beleza_pura.entities.Avaliacao;
import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.AvaliacaoRepository;
import beleza_pura.com.example.beleza_pura.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.ProfissionalRepository;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoUsecase {
    private final AvaliacaoRepository avaliacaoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProfissionalRepository profissionalRepository;

    public AvaliacaoUsecase(AvaliacaoRepository avaliacaoRepository,
                            EstabelecimentoRepository estabelecimentoRepository,
                            ProfissionalRepository profissionalRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public Avaliacao registrarAvaliacao(RegistrarAvaliacaoRequisicao requisicao) {
        Estabelecimento estabelecimento = estabelecimentoRepository.buscaPorId(requisicao.getEstabelecimentoId())
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));

        Profissional profissional = profissionalRepository.buscaPorId(requisicao.getProfissionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));

        Avaliacao avaliacao = Avaliacao.builder()
                .estabelecimento(estabelecimento)
                .profissional(profissional)
                .nota(requisicao.getNota())
                .comentario(requisicao.getComentario())
                .build();

        return avaliacaoRepository.salvar(avaliacao);
    }
}