package beleza_pura.com.example.beleza_pura.infrastructure.web.controllers;

import beleza_pura.com.example.beleza_pura.application.services.EstabelecimentoService;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import beleza_pura.com.example.beleza_pura.application.dtos.ProfissionalDisponibilidadeDTO;
import beleza_pura.com.example.beleza_pura.application.services.DisponibilidadeService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/estabelecimentos/{estabelecimentoId}/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadeController {
    private final EstabelecimentoService estabelecimentoService;
    private final DisponibilidadeService disponibilidadeService;

    @GetMapping
    public ResponseEntity<List<ProfissionalDisponibilidadeDTO>> getDisponibilidades(
            @PathVariable Long estabelecimentoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ) {
        // Get estabelecimento without causing circular mapping
        Estabelecimento estabelecimento = estabelecimentoService.findById(estabelecimentoId);

        List<ProfissionalDisponibilidadeDTO> disponibilidades =
                disponibilidadeService.getDisponibilidadeProfissionais(estabelecimento.getId(), data);

        return ResponseEntity.ok(disponibilidades);
    }
}