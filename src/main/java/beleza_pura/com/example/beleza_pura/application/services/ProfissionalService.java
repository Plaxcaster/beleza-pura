package beleza_pura.com.example.beleza_pura.application.services;

import beleza_pura.com.example.beleza_pura.domain.models.Disponibilidade;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers.DisponibilidadeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import beleza_pura.com.example.beleza_pura.application.dtos.NovoProfissionalDTO;
import beleza_pura.com.example.beleza_pura.application.dtos.ProfissionalDTO;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.domain.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.domain.repositories.ProfissionalRepository;

import java.util.Set;

@Service
@Transactional
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepo;
    private final EstabelecimentoRepository estabelecimentoRepo;
    private final DisponibilidadeMapper disponibilidadeMapper;

    public ProfissionalService(ProfissionalRepository profissionalRepo,
                               EstabelecimentoRepository estabelecimentoRepo,
                               DisponibilidadeMapper disponibilidadeMapper) {
        this.profissionalRepo = profissionalRepo;
        this.estabelecimentoRepo = estabelecimentoRepo;
        this.disponibilidadeMapper = disponibilidadeMapper;
    }

    public ProfissionalDTO registrarProfissional(NovoProfissionalDTO dto) {
        Estabelecimento estabelecimento = estabelecimentoRepo.findById(dto.estabelecimentoId())
                .orElseThrow(() -> new IllegalArgumentException("Estabelecimento não encontrado"));

        // Convert first disponibilidade from DTO to domain model
        NovoProfissionalDTO.DisponibilidadeRequest firstDisponibilidade = dto.disponibilidade().iterator().next();
        Disponibilidade domainDisponibilidade = new Disponibilidade();
        domainDisponibilidade.setDiasTrabalho(firstDisponibilidade.diaSemana().toString());
        domainDisponibilidade.setHoraInicio(firstDisponibilidade.horaInicio().toString());
        domainDisponibilidade.setHoraFim(firstDisponibilidade.horaFim().toString());

        Profissional profissional = new Profissional();
        profissional.setNome(dto.nome());
        profissional.setEspecialidades(dto.especialidades());
        profissional.setEstabelecimento(estabelecimento);
        profissional.setDisponibilidade(domainDisponibilidade);

        Profissional savedProfissional = profissionalRepo.save(profissional);

        // Convert back to DTO structure
        Set<ProfissionalDTO.DisponibilidadeResponse> disponibilidadeResponse = Set.of(
                new ProfissionalDTO.DisponibilidadeResponse(
                        firstDisponibilidade.diaSemana(),
                        firstDisponibilidade.horaInicio(),
                        firstDisponibilidade.horaFim()
                )
        );

        return new ProfissionalDTO(
                savedProfissional.getId(),
                savedProfissional.getNome(),
                savedProfissional.getEspecialidades(),
                savedProfissional.getEstabelecimento().getId(),
                disponibilidadeResponse,
                dto.valorHora()
        );
    }
}