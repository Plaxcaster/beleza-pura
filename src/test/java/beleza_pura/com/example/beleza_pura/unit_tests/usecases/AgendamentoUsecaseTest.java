package beleza_pura.com.example.beleza_pura.unit_tests.usecases;

import beleza_pura.com.example.beleza_pura.data.MarcarAgendamentoRequisicao;
import beleza_pura.com.example.beleza_pura.entities.*;
import beleza_pura.com.example.beleza_pura.exceptions.AgendamentoOperationException;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.*;
import beleza_pura.com.example.beleza_pura.usecases.AgendamentoUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AgendamentoUsecaseTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private EspecialidadeRepository especialidadeRepository;

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private AgendamentoUsecase agendamentoUsecase;

    private UUID clienteId;
    private UUID profissionalId;
    private UUID especialidadeId;
    private UUID estabelecimentoId;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private Profissional profissional;
    private Especialidade especialidade;
    private Estabelecimento estabelecimento;
    private Agendamento agendamento;
    private MarcarAgendamentoRequisicao requisicao;

    @BeforeEach
    void setUp() {
        clienteId = UUID.randomUUID();
        profissionalId = UUID.randomUUID();
        especialidadeId = UUID.randomUUID();
        estabelecimentoId = UUID.randomUUID();
        dataHora = LocalDateTime.of(2023, 12, 15, 14, 0);

        // Initialize Cliente
        cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setTelefone("123456789");

        // Initialize Especialidade
        especialidade = new Especialidade();
        especialidade.setId(especialidadeId);
        especialidade.setNome("Corte de Cabelo");

        // Initialize Estabelecimento
        estabelecimento = new Estabelecimento();
        estabelecimento.setId(estabelecimentoId);
        estabelecimento.setNome("Salão da Maria");
        estabelecimento.setEndereco("Rua Teste, 123");

        // Initialize HorarioAtendimento
        HorarioAtendimento horario = new HorarioAtendimento(
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        // Initialize Profissional
        profissional = new Profissional(
                "Profissional Teste",
                List.of(especialidade),
                horario,
                new BigDecimal("50.00")
        );
        profissional.setId(profissionalId);


        // Initialize Requisicao
        requisicao = new MarcarAgendamentoRequisicao();
        requisicao.setClienteId(clienteId);
        requisicao.setProfissionalId(profissionalId);
        requisicao.setEspecialidadeId(especialidadeId);
        requisicao.setEstabelecimentoId(estabelecimentoId);
        requisicao.setDataHora(dataHora);

        // Initialize Agendamento
        agendamento = Agendamento.builder()
                .id(1L)
                .cliente(cliente)
                .profissional(profissional)
                .especialidade(especialidade)
                .estabelecimento(estabelecimento)
                .dataHora(dataHora)
                .status(StatusAgendamento.AGENDADO)
                .build();
    }

    @Test
    void marcarAgendamento_ClienteNotFound() {
        when(clienteRepository.buscaPorId(eq(clienteId))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                agendamentoUsecase.marcarAgendamento(requisicao));
    }

    @Test
    void marcarAgendamento_ProfissionalNotFound() {
        when(clienteRepository.buscaPorId(eq(clienteId))).thenReturn(Optional.of(cliente));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                agendamentoUsecase.marcarAgendamento(requisicao));
    }

    @Test
    void marcarAgendamento_EspecialidadeNotFound() {
        when(clienteRepository.buscaPorId(eq(clienteId))).thenReturn(Optional.of(cliente));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(especialidadeRepository.buscaPorId(eq(especialidadeId))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                agendamentoUsecase.marcarAgendamento(requisicao));
    }

    @Test
    void marcarAgendamento_EstabelecimentoNotFound() {
        when(clienteRepository.buscaPorId(eq(clienteId))).thenReturn(Optional.of(cliente));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(especialidadeRepository.buscaPorId(eq(especialidadeId))).thenReturn(Optional.of(especialidade));
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                agendamentoUsecase.marcarAgendamento(requisicao));
    }

    @Test
    void marcarAgendamento_ProfissionalNotAvailable() {
        when(clienteRepository.buscaPorId(eq(clienteId))).thenReturn(Optional.of(cliente));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(especialidadeRepository.buscaPorId(eq(especialidadeId))).thenReturn(Optional.of(especialidade));
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(agendamentoRepository.existeAgendamentoNoHorario(eq(profissionalId), eq(dataHora))).thenReturn(true);

        assertThrows(IllegalStateException.class, () ->
                agendamentoUsecase.marcarAgendamento(requisicao));
    }

    @Test
    void marcarAgendamento_ProfissionalOutsideWorkingHours() {
        LocalDateTime outsideHours = LocalDateTime.of(2023, 12, 15, 8, 0);
        MarcarAgendamentoRequisicao req = new MarcarAgendamentoRequisicao();
        req.setClienteId(clienteId);
        req.setProfissionalId(profissionalId);
        req.setEspecialidadeId(especialidadeId);
        req.setEstabelecimentoId(estabelecimentoId);
        req.setDataHora(outsideHours);

        when(clienteRepository.buscaPorId(eq(clienteId))).thenReturn(Optional.of(cliente));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(especialidadeRepository.buscaPorId(eq(especialidadeId))).thenReturn(Optional.of(especialidade));
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));

        assertThrows(IllegalStateException.class, () ->
                agendamentoUsecase.marcarAgendamento(req));
    }

    @Test
    void marcarAgendamento_Success() {
        when(clienteRepository.buscaPorId(eq(clienteId))).thenReturn(Optional.of(cliente));
        when(profissionalRepository.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(especialidadeRepository.buscaPorId(eq(especialidadeId))).thenReturn(Optional.of(especialidade));
        when(estabelecimentoRepository.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(agendamentoRepository.existeAgendamentoNoHorario(eq(profissionalId), eq(dataHora))).thenReturn(false);
        when(agendamentoRepository.salvar(any(Agendamento.class))).thenReturn(agendamento);

        Agendamento result = agendamentoUsecase.marcarAgendamento(requisicao);

        assertNotNull(result);
        assertEquals(cliente, result.getCliente());
        assertEquals(profissional, result.getProfissional());
        assertEquals(especialidade, result.getEspecialidade());
        assertEquals(estabelecimento, result.getEstabelecimento());
        assertEquals(dataHora, result.getDataHora());
        assertEquals(StatusAgendamento.AGENDADO, result.getStatus());
    }

    @Test
    void atualizarStatusAgendamento_NotFound() {
        when(agendamentoRepository.buscarPorId(eq(1L))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                agendamentoUsecase.atualizarStatusAgendamento(1L, StatusAgendamento.CONCLUIDO));
    }

    @Test
    void cancelarAgendamento_Success() {
        when(agendamentoRepository.buscarPorId(eq(1L))).thenReturn(Optional.of(agendamento));
        when(agendamentoRepository.salvar(any(Agendamento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Agendamento result = agendamentoUsecase.cancelarAgendamento(1L);

        assertNotNull(result);
        assertEquals(StatusAgendamento.CANCELADO, result.getStatus());
    }

    @Test
    void cancelarAgendamento_NotFound() {
        when(agendamentoRepository.buscarPorId(eq(1L))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                agendamentoUsecase.cancelarAgendamento(1L));
    }

    @Test
    void cancelarAgendamento_AlreadyCompleted() {
        agendamento.setStatus(StatusAgendamento.CONCLUIDO);
        when(agendamentoRepository.buscarPorId(eq(1L))).thenReturn(Optional.of(agendamento));

        assertThrows(AgendamentoOperationException.class, () ->
                agendamentoUsecase.cancelarAgendamento(1L));
    }

    @Test
    void cancelarAgendamento_AlreadyCanceled() {
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        when(agendamentoRepository.buscarPorId(eq(1L))).thenReturn(Optional.of(agendamento));

        assertThrows(AgendamentoOperationException.class, () ->
                agendamentoUsecase.cancelarAgendamento(1L));
    }
}