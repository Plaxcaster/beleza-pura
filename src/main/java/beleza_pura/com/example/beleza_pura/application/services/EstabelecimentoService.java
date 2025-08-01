package beleza_pura.com.example.beleza_pura.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import beleza_pura.com.example.beleza_pura.application.dtos.EstabelecimentoDTO;
import beleza_pura.com.example.beleza_pura.application.dtos.NovoEstabelecimentoDTO;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import beleza_pura.com.example.beleza_pura.domain.models.HorarioFuncionamento;
import beleza_pura.com.example.beleza_pura.domain.repositories.EstabelecimentoRepository;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;

    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Transactional
    public EstabelecimentoDTO registrarEstabelecimento(NovoEstabelecimentoDTO dto) {
        // Convert DTO to domain model
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome(dto.nome());
        estabelecimento.setEndereco(dto.endereco());
        estabelecimento.setFotos(dto.fotos());

        // Set opening hours (using top-level class)
        HorarioFuncionamento horario = new HorarioFuncionamento();
        horario.setAbertura(dto.horarioFuncionamento().abertura());
        horario.setFechamento(dto.horarioFuncionamento().fechamento());
        estabelecimento.setHorarioFuncionamento(horario);

        // Save the establishment
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);

        // Convert to response DTO
        return convertToDTO(savedEstabelecimento);
    }

    private EstabelecimentoDTO convertToDTO(Estabelecimento estabelecimento) {
        return new EstabelecimentoDTO(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getEndereco(),
                estabelecimento.getFotos(),
                new EstabelecimentoDTO.HorarioFuncionamentoDTO(
                        estabelecimento.getHorarioFuncionamento().getAbertura(),
                        estabelecimento.getHorarioFuncionamento().getFechamento()
                )
        );
    }
}