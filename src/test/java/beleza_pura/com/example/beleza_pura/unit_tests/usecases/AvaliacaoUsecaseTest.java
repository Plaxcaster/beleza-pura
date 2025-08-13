package beleza_pura.com.example.beleza_pura.unit_tests.usecases;

import beleza_pura.com.example.beleza_pura.data.RegistrarAvaliacaoRequisicao;
import beleza_pura.com.example.beleza_pura.entities.*;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.*;
import beleza_pura.com.example.beleza_pura.usecases.AvaliacaoUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AvaliacaoUsecaseTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @InjectMocks
    private AvaliacaoUsecase avaliacaoUsecase;

    private UUID profissionalId;
    private UUID estabelecimentoId;
    private Profissional profissional;
    private Estabelecimento estabelecimento;
    private Avaliacao avaliacao;
    private RegistrarAvaliacaoRequisicao requisicao;

    @BeforeEach
    void setUp() {
        profissionalId = UUID.randomUUID();
        estabelecimentoId = UUID.randomUUID();

        // Initialize HorarioAtendimento
        HorarioAtendimento horario = new HorarioAtendimento(
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        // Initialize Especialidade for Profissional
        Especialidade especialidade = new Especialidade();
        especialidade.setId(UUID.randomUUID());
        especialidade.setNome("Corte de Cabelo");

        // Initialize Profissional
        profissional = Profissional.builder()
                .nome("Profissional Teste")
                .especialidades(List.of(especialidade))
                .horario(horario)
                .tarifa(new BigDecimal("50.00"))
                .build();
        profissional.setId(profissionalId);

        // Initialize Estabelecimento
        estabelecimento = Estabelecimento.builder()
                .nome("Salão da Maria")
                .endereco("Rua Teste, 123")
                .horarioAtendimento(horario)
                .profissionais(Set.of())
                .build();
        estabelecimento.setId(estabelecimentoId);

        // Initialize RegistrarAvaliacaoRequisicao
        requisicao = new RegistrarAvaliacaoRequisicao();
        requisicao.setEstabelecimentoId(estabelecimentoId);
        requisicao.setProfissionalId(profissionalId);
        requisicao.setNota(8);
        requisicao.setComentario("Excelente atendimento!");

        // Initialize Avaliacao
        avaliacao = Avaliacao.builder()
                .id(UUID.randomUUID())
                .estabelecimento(estabelecimento)
                .profissional(profissional)
                .nota(8)
                .comentario("Excelente atendimento!")
                .build();
    }

    @Test
    void registrarAvaliacao_Success() {
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals(estabelecimento, result.getEstabelecimento());
        assertEquals(profissional, result.getProfissional());
        assertEquals(8, result.getNota());
        assertEquals("Excelente atendimento!", result.getComentario());

        verify(estabelecimentoRepository).buscaPorId(eq(estabelecimentoId));
        verify(profissionalRepository).buscaPorId(eq(profissionalId));
        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_EstabelecimentoNotFound() {
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                avaliacaoUsecase.registrarAvaliacao(requisicao));

        assertEquals("Estabelecimento não encontrado", exception.getMessage());

        verify(estabelecimentoRepository).buscaPorId(eq(estabelecimentoId));
        verify(profissionalRepository, never()).buscaPorId(any());
        verify(avaliacaoRepository, never()).salvar(any());
    }

    @Test
    void registrarAvaliacao_ProfissionalNotFound() {
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                avaliacaoUsecase.registrarAvaliacao(requisicao));

        assertEquals("Profissional não encontrado", exception.getMessage());

        verify(estabelecimentoRepository).buscaPorId(eq(estabelecimentoId));
        verify(profissionalRepository).buscaPorId(eq(profissionalId));
        verify(avaliacaoRepository, never()).salvar(any());
    }

    @Test
    void registrarAvaliacao_WithMinimumRating() {
        requisicao.setNota(1);
        requisicao.setComentario("Atendimento muito ruim");

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals(1, result.getNota());
        assertEquals("Atendimento muito ruim", result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_WithMaximumRating() {
        requisicao.setNota(10);
        requisicao.setComentario("Perfeito!");

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals(10, result.getNota());
        assertEquals("Perfeito!", result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_WithMidRangeRating() {
        requisicao.setNota(5);
        requisicao.setComentario("Atendimento médio");

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals(5, result.getNota());
        assertEquals("Atendimento médio", result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_WithEmptyComment() {
        requisicao.setComentario("");

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals("", result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_WithNullComment() {
        requisicao.setComentario(null);

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertNull(result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_WithLongComment() {
        String longComment = "Este é um comentário muito longo sobre o atendimento recebido. " +
                "O profissional foi muito atencioso e prestativo durante todo o processo. " +
                "O estabelecimento também possui uma estrutura muito boa e ambiente agradável. " +
                "Recomendo fortemente este local para quem busca qualidade no atendimento. " +
                "A experiência foi excepcional e superou todas as minhas expectativas.";

        requisicao.setComentario(longComment);

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals(longComment, result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_ValidatesAvaliacaoBuilding() {
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);

            // Verify that the Avaliacao object is built correctly
            assertEquals(estabelecimento, avaliacaoArgument.getEstabelecimento());
            assertEquals(profissional, avaliacaoArgument.getProfissional());
            assertEquals(requisicao.getNota(), avaliacaoArgument.getNota());
            assertEquals(requisicao.getComentario(), avaliacaoArgument.getComentario());

            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_WithAllRatings1to10() {
        int[] ratings = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int rating : ratings) {
            // Reset mocks for each iteration
            reset(estabelecimentoRepository, profissionalRepository, avaliacaoRepository);

            requisicao.setNota(rating);
            requisicao.setComentario("Comentário para nota " + rating);

            when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
            when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
            when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
                Avaliacao avaliacaoArgument = invocation.getArgument(0);
                return Avaliacao.builder()
                        .id(UUID.randomUUID())
                        .estabelecimento(avaliacaoArgument.getEstabelecimento())
                        .profissional(avaliacaoArgument.getProfissional())
                        .nota(avaliacaoArgument.getNota())
                        .comentario(avaliacaoArgument.getComentario())
                        .build();
            });

            Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

            assertNotNull(result);
            assertEquals(rating, result.getNota());
            assertEquals("Comentário para nota " + rating, result.getComentario());
        }
    }

    @Test
    void registrarAvaliacao_WithLowRating() {
        requisicao.setNota(2);
        requisicao.setComentario("Atendimento ruim, não recomendo");

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals(2, result.getNota());
        assertEquals("Atendimento ruim, não recomendo", result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }

    @Test
    void registrarAvaliacao_WithHighRating() {
        requisicao.setNota(9);
        requisicao.setComentario("Quase perfeito, apenas pequenos detalhes");

        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(avaliacaoRepository.salvar(any(Avaliacao.class))).thenAnswer(invocation -> {
            Avaliacao avaliacaoArgument = invocation.getArgument(0);
            return Avaliacao.builder()
                    .id(UUID.randomUUID())
                    .estabelecimento(avaliacaoArgument.getEstabelecimento())
                    .profissional(avaliacaoArgument.getProfissional())
                    .nota(avaliacaoArgument.getNota())
                    .comentario(avaliacaoArgument.getComentario())
                    .build();
        });

        Avaliacao result = avaliacaoUsecase.registrarAvaliacao(requisicao);

        assertNotNull(result);
        assertEquals(9, result.getNota());
        assertEquals("Quase perfeito, apenas pequenos detalhes", result.getComentario());

        verify(avaliacaoRepository).salvar(any(Avaliacao.class));
    }
}