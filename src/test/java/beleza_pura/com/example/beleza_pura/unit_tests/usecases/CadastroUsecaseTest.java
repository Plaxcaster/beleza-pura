package beleza_pura.com.example.beleza_pura.unit_tests.usecases;

import beleza_pura.com.example.beleza_pura.data.*;
import beleza_pura.com.example.beleza_pura.entities.*;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.*;
import beleza_pura.com.example.beleza_pura.usecases.CadastroUsecase;
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
class CadastroUsecaseTest {

    @Mock
    private ProfissionalRepository repositoryProfissional;

    @Mock
    private EspecialidadeRepository repositoryEspecialidade;

    @Mock
    private EstabelecimentoRepository repositoryEstabelecimento;

    @Mock
    private ClienteRepository repositoryCliente;

    @InjectMocks
    private CadastroUsecase cadastroUsecase;

    private UUID profissionalId;
    private UUID especialidadeId;
    private UUID estabelecimentoId;
    private UUID clienteId;
    private Profissional profissional;
    private Especialidade especialidade;
    private Estabelecimento estabelecimento;
    private Cliente cliente;
    private CadastrarProfissionalRequisicao profissionalRequisicao;
    private CadastrarClienteRequisicao clienteRequisicao;
    private CadastrarEspecialidadeRequisicao especialidadeRequisicao;
    private CadastrarEstabelecimentoRequisicao estabelecimentoRequisicao;
    private VincularEspecialidadeRequisicao vincularEspecialidadeRequisicao;
    private VincularEstabelecimentoRequisicao vincularEstabelecimentoRequisicao;

    @BeforeEach
    void setUp() {
        profissionalId = UUID.randomUUID();
        especialidadeId = UUID.randomUUID();
        estabelecimentoId = UUID.randomUUID();
        clienteId = UUID.randomUUID();

        // Initialize entities
        HorarioAtendimento horario = new HorarioAtendimento(
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        profissional = Profissional.builder()
                .nome("Profissional Teste")
                .especialidades(List.of())
                .horario(horario)
                .tarifa(new BigDecimal("50.00"))
                .build();
        profissional.setId(profissionalId);

        especialidade = Especialidade.builder()
                .nome("Corte de Cabelo")
                .build();
        especialidade.setId(especialidadeId);

        estabelecimento = Estabelecimento.builder()
                .nome("Salão da Maria")
                .endereco("Rua Teste, 123")
                .horarioAtendimento(horario)
                .profissionais(Set.of())
                .build();
        estabelecimento.setId(estabelecimentoId);

        cliente = Cliente.builder()
                .nome("Cliente Teste")
                .email("cliente@teste.com")
                .telefone("123456789")
                .build();
        cliente.setId(clienteId);

        // Initialize request objects
        profissionalRequisicao = new CadastrarProfissionalRequisicao();
        profissionalRequisicao.setNome("Profissional Teste");
        profissionalRequisicao.setHorarioInicio("09:00");
        profissionalRequisicao.setHorarioFim("18:00");
        profissionalRequisicao.setTarifa(new BigDecimal("50.00"));

        clienteRequisicao = new CadastrarClienteRequisicao();
        clienteRequisicao.setNome("Cliente Teste");
        clienteRequisicao.setEmail("cliente@teste.com");
        clienteRequisicao.setTelefone("123456789");

        especialidadeRequisicao = new CadastrarEspecialidadeRequisicao();
        especialidadeRequisicao.setNome("Corte de Cabelo");

        estabelecimentoRequisicao = new CadastrarEstabelecimentoRequisicao();
        estabelecimentoRequisicao.setNome("Salão da Maria");
        estabelecimentoRequisicao.setEndereco("Rua Teste, 123");
        estabelecimentoRequisicao.setHorarioInicio("09:00");
        estabelecimentoRequisicao.setHorarioFim("18:00");

        vincularEspecialidadeRequisicao = new VincularEspecialidadeRequisicao();
        vincularEspecialidadeRequisicao.setIdProfissional(profissionalId);
        vincularEspecialidadeRequisicao.setListaEspecialidades(List.of(especialidadeId));

        vincularEstabelecimentoRequisicao = new VincularEstabelecimentoRequisicao();
        vincularEstabelecimentoRequisicao.setIdEstabelecimento(estabelecimentoId);
        vincularEstabelecimentoRequisicao.setListaProfissionais(List.of(profissionalId));
    }

    @Test
    void cadastrarProfissional_Success() {
        when(repositoryProfissional.cadastrar(any(Profissional.class))).thenReturn(profissional);

        Profissional result = cadastroUsecase.cadastrarProfissional(profissionalRequisicao);

        assertNotNull(result);
        assertEquals("Profissional Teste", result.getNome());
        assertEquals(new BigDecimal("50.00"), result.getTarifa());
        assertEquals(LocalTime.of(9, 0), result.getHorario().getHorarioInicio());
        assertEquals(LocalTime.of(18, 0), result.getHorario().getHorarioFim());
        assertTrue(result.getEspecialidades().isEmpty());

        verify(repositoryProfissional).cadastrar(any(Profissional.class));
    }

    @Test
    void cadastrarProfissional_InvalidTimeFormat() {
        profissionalRequisicao.setHorarioInicio("invalid-time");

        assertThrows(IllegalArgumentException.class, () ->
                cadastroUsecase.cadastrarProfissional(profissionalRequisicao));
    }

    @Test
    void cadastrarCliente_Success() {
        when(repositoryCliente.cadastrar(any(Cliente.class))).thenReturn(cliente);

        Cliente result = cadastroUsecase.cadastrarCliente(clienteRequisicao);

        assertNotNull(result);
        assertEquals("Cliente Teste", result.getNome());
        assertEquals("cliente@teste.com", result.getEmail());
        assertEquals("123456789", result.getTelefone());

        verify(repositoryCliente).cadastrar(any(Cliente.class));
    }

    @Test
    void cadastrarEspecialidade_Success() {
        when(repositoryEspecialidade.cadastrar(any(Especialidade.class))).thenReturn(especialidade);

        Especialidade result = cadastroUsecase.cadastrarEspecialidade(especialidadeRequisicao);

        assertNotNull(result);
        assertEquals("Corte de Cabelo", result.getNome());

        verify(repositoryEspecialidade).cadastrar(any(Especialidade.class));
    }

    @Test
    void cadastrarEstabelecimento_Success() {
        when(repositoryEstabelecimento.cadastrar(any(Estabelecimento.class))).thenReturn(estabelecimento);

        Estabelecimento result = cadastroUsecase.cadastrarEstabelecimento(estabelecimentoRequisicao);

        assertNotNull(result);
        assertEquals("Salão da Maria", result.getNome());
        assertEquals("Rua Teste, 123", result.getEndereco());
        assertEquals(LocalTime.of(9, 0), result.getHorarioAtendimento().getHorarioInicio());
        assertEquals(LocalTime.of(18, 0), result.getHorarioAtendimento().getHorarioFim());
        assertTrue(result.getProfissionais().isEmpty());

        verify(repositoryEstabelecimento).cadastrar(any(Estabelecimento.class));
    }

    @Test
    void cadastrarEstabelecimento_InvalidTimeFormat() {
        estabelecimentoRequisicao.setHorarioInicio("25:00");

        assertThrows(IllegalArgumentException.class, () ->
                cadastroUsecase.cadastrarEstabelecimento(estabelecimentoRequisicao));
    }

    @Test
    void vincularEspecialidades_Success() {
        when(repositoryProfissional.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(repositoryEspecialidade.buscaPorId(eq(especialidadeId))).thenReturn(Optional.of(especialidade));
        when(repositoryProfissional.salvar(any(Profissional.class))).thenAnswer(invocation -> {
            Profissional prof = invocation.getArgument(0);
            prof.setEspecialidades(List.of(especialidade));
            return prof;
        });

        Profissional result = cadastroUsecase.vincularEspecialidades(vincularEspecialidadeRequisicao);

        assertNotNull(result);
        assertFalse(result.getEspecialidades().isEmpty());
        assertEquals(1, result.getEspecialidades().size());
        assertEquals("Corte de Cabelo", result.getEspecialidades().get(0).getNome());

        verify(repositoryProfissional).buscaPorId(eq(profissionalId));
        verify(repositoryEspecialidade).buscaPorId(eq(especialidadeId));
        verify(repositoryProfissional).salvar(any(Profissional.class));
    }

    @Test
    void vincularEspecialidades_ProfissionalNotFound() {
        when(repositoryProfissional.buscaPorId(eq(profissionalId))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                cadastroUsecase.vincularEspecialidades(vincularEspecialidadeRequisicao));

        verify(repositoryProfissional).buscaPorId(eq(profissionalId));
        verify(repositoryEspecialidade, never()).buscaPorId(any());
        verify(repositoryProfissional, never()).salvar(any());
    }

    @Test
    void vincularEspecialidades_EspecialidadeNotFound() {
        when(repositoryProfissional.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(repositoryEspecialidade.buscaPorId(eq(especialidadeId))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                cadastroUsecase.vincularEspecialidades(vincularEspecialidadeRequisicao));

        verify(repositoryProfissional).buscaPorId(eq(profissionalId));
        verify(repositoryEspecialidade).buscaPorId(eq(especialidadeId));
        verify(repositoryProfissional, never()).salvar(any());
    }

    @Test
    void vincularEspecialidades_MultipleEspecialidades() {
        UUID especialidade2Id = UUID.randomUUID();
        Especialidade especialidade2 = Especialidade.builder().nome("Manicure").build();
        especialidade2.setId(especialidade2Id);

        vincularEspecialidadeRequisicao.setListaEspecialidades(List.of(especialidadeId, especialidade2Id));

        when(repositoryProfissional.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(repositoryEspecialidade.buscaPorId(eq(especialidadeId))).thenReturn(Optional.of(especialidade));
        when(repositoryEspecialidade.buscaPorId(eq(especialidade2Id))).thenReturn(Optional.of(especialidade2));
        when(repositoryProfissional.salvar(any(Profissional.class))).thenAnswer(invocation -> {
            Profissional prof = invocation.getArgument(0);
            prof.setEspecialidades(List.of(especialidade, especialidade2));
            return prof;
        });

        Profissional result = cadastroUsecase.vincularEspecialidades(vincularEspecialidadeRequisicao);

        assertNotNull(result);
        assertEquals(2, result.getEspecialidades().size());

        verify(repositoryEspecialidade, times(2)).buscaPorId(any());
    }

    @Test
    void vincularEstabelecimento_Success() {
        when(repositoryEstabelecimento.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(repositoryProfissional.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(repositoryEstabelecimento.salvar(any(Estabelecimento.class))).thenAnswer(invocation -> {
            Estabelecimento estab = invocation.getArgument(0);
            estab.setProfissionais(Set.of(profissional));
            return estab;
        });

        Estabelecimento result = cadastroUsecase.vincularEstabelecimento(vincularEstabelecimentoRequisicao);

        assertNotNull(result);
        assertFalse(result.getProfissionais().isEmpty());
        assertEquals(1, result.getProfissionais().size());
        assertTrue(result.getProfissionais().contains(profissional));

        verify(repositoryEstabelecimento).buscaPorId(eq(estabelecimentoId));
        verify(repositoryProfissional).buscaPorId(eq(profissionalId));
        verify(repositoryEstabelecimento).salvar(any(Estabelecimento.class));
    }

    @Test
    void vincularEstabelecimento_EstabelecimentoNotFound() {
        when(repositoryEstabelecimento.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                cadastroUsecase.vincularEstabelecimento(vincularEstabelecimentoRequisicao));

        verify(repositoryEstabelecimento).buscaPorId(eq(estabelecimentoId));
        verify(repositoryProfissional, never()).buscaPorId(any());
        verify(repositoryEstabelecimento, never()).salvar(any());
    }

    @Test
    void vincularEstabelecimento_ProfissionalNotFound() {
        when(repositoryEstabelecimento.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(repositoryProfissional.buscaPorId(eq(profissionalId))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                cadastroUsecase.vincularEstabelecimento(vincularEstabelecimentoRequisicao));

        verify(repositoryEstabelecimento).buscaPorId(eq(estabelecimentoId));
        verify(repositoryProfissional).buscaPorId(eq(profissionalId));
        verify(repositoryEstabelecimento, never()).salvar(any());
    }

    @Test
    void vincularEstabelecimento_MultipleProfissionais() {
        UUID profissional2Id = UUID.randomUUID();
        Profissional profissional2 = Profissional.builder()
                .nome("Profissional 2")
                .especialidades(List.of())
                .horario(new HorarioAtendimento(LocalTime.of(8, 0), LocalTime.of(17, 0)))
                .tarifa(new BigDecimal("40.00"))
                .build();
        profissional2.setId(profissional2Id);

        vincularEstabelecimentoRequisicao.setListaProfissionais(List.of(profissionalId, profissional2Id));

        when(repositoryEstabelecimento.buscaPorId(eq(estabelecimentoId))).thenReturn(Optional.of(estabelecimento));
        when(repositoryProfissional.buscaPorId(eq(profissionalId))).thenReturn(Optional.of(profissional));
        when(repositoryProfissional.buscaPorId(eq(profissional2Id))).thenReturn(Optional.of(profissional2));
        when(repositoryEstabelecimento.salvar(any(Estabelecimento.class))).thenAnswer(invocation -> {
            Estabelecimento estab = invocation.getArgument(0);
            estab.setProfissionais(Set.of(profissional, profissional2));
            return estab;
        });

        Estabelecimento result = cadastroUsecase.vincularEstabelecimento(vincularEstabelecimentoRequisicao);

        assertNotNull(result);
        assertEquals(2, result.getProfissionais().size());
        assertTrue(result.getProfissionais().contains(profissional));
        assertTrue(result.getProfissionais().contains(profissional2));

        verify(repositoryProfissional, times(2)).buscaPorId(any());
    }
}