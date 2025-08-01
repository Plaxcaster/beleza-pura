package beleza_pura.com.example.beleza_pura.application.dtos;

import java.util.Set;

public record EstabelecimentoDTO(
        Long id,
        String nome,
        String endereco,
        Set<String> fotos,
        HorarioFuncionamentoDTO horarioFuncionamento
) {
    public record HorarioFuncionamentoDTO(
            String abertura,
            String fechamento
    ) {}
}