package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.ProfissionalRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
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
        try {
            return estabelecimentoRepository.buscaPorId(converteUUID(estabelecimentoId));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Estabelecimento não encontrado", e);
        }
    }

    public Profissional consultaProfissionalPorId(String id_profissional) {
        try {
            return profissionalRepository.buscaPorId(converteUUID(id_profissional));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Estabelecimento não encontrado", e);
        }

    }

    private UUID converteUUID(String estabelecimentoId) {
        try {
            return UUID.fromString(estabelecimentoId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID Informada não é válida", e);
        }
    }

}
