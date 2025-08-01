package beleza_pura.com.example.beleza_pura.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import beleza_pura.com.example.beleza_pura.application.dtos.NovoProfissionalDTO;
import beleza_pura.com.example.beleza_pura.application.dtos.ProfissionalDTO;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.domain.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.domain.repositories.ProfissionalRepository;

@Service
@Transactional
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepo;
    private final EstabelecimentoRepository estabelecimentoRepo;

    public ProfissionalService(ProfissionalRepository profissionalRepo,
                               EstabelecimentoRepository estabelecimentoRepo) {
        this.profissionalRepo = profissionalRepo;
        this.estabelecimentoRepo = estabelecimentoRepo;
    }

    public ProfissionalDTO registrarProfissional(NovoProfissionalDTO dto) {
        Estabelecimento estabelecimento = estabelecimentoRepo.findById(dto.estabelecimentoId())
                .orElseThrow(() -> new IllegalArgumentException("Estabelecimento não encontrado"));

        Profissional profissional = new Profissional();
        profissional.setNome(dto.nome());
        profissional.setEspecialidades(dto.especialidades());
        profissional.setEstabelecimento(estabelecimento);
        profissional.setDisponibilidade(dto.disponibilidade());

        Profissional savedProfissional = profissionalRepo.save(profissional);

        return new ProfissionalDTO(
                savedProfissional.getId(),
                savedProfissional.getNome(),
                savedProfissional.getEspecialidades(),
                savedProfissional.getEstabelecimento().getId(),
                savedProfissional.getDisponibilidade()
        );
    }
}