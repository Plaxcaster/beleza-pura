package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.ProfissionalRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConsultaUsecase {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProfissionalRepository profissionalRepository;

    public ConsultaUsecase(EstabelecimentoRepository estabelecimentoRepository,
                           ProfissionalRepository profissionalRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public Estabelecimento consultaEstabelecimentoPorId(String estabelecimentoId) {
        UUID id = converteUUID(estabelecimentoId);
        return estabelecimentoRepository.buscaPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    }

    public Profissional consultaProfissionalPorId(String idProfissional) {
        UUID id = converteUUID(idProfissional);
        return profissionalRepository.buscaPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));
    }

    private UUID converteUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID informada não é válida", e);
        }
    }
}