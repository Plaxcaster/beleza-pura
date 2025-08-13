package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.entities.Agendamento;
import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.entities.StatusAgendamento;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.AgendamentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.ProfissionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaUsecaseTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private ConsultaUsecase consultaUsecase;

    private UUID validUUID;
    private String validUUIDString;
    private String invalidUUIDString;
    private Estabelecimento estabelecimentoMock;
    private Profissional profissionalMock;
    private List<Agendamento> agendamentosMock;

    @BeforeEach
    void setUp() {
        validUUID = UUID.randomUUID();
        validUUIDString = validUUID.toString();
        invalidUUIDString = "invalid-uuid";

        estabelecimentoMock = mock(Estabelecimento.class);
        profissionalMock = mock(Profissional.class);
        agendamentosMock = Arrays.asList(
                mock(Agendamento.class),
                mock(Agendamento.class)
        );
    }

    // Testes para consultaEstabelecimentoPorId
    @Test
    void consultaEstabelecimentoPorId_ComIdValido_DeveRetornarEstabelecimento() {
        // Arrange
        when(estabelecimentoRepository.buscaPorId(validUUID))
                .thenReturn(Optional.of(estabelecimentoMock));

        // Act
        Estabelecimento resultado = consultaUsecase.consultaEstabelecimentoPorId(validUUIDString);

        // Assert
        assertNotNull(resultado);
        assertEquals(estabelecimentoMock, resultado);
        verify(estabelecimentoRepository, times(1)).buscaPorId(validUUID);
    }

    @Test
    void consultaEstabelecimentoPorId_ComIdInexistente_DeveLancarEntityNotFoundException() {
        // Arrange
        when(estabelecimentoRepository.buscaPorId(validUUID))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> consultaUsecase.consultaEstabelecimentoPorId(validUUIDString)
        );

        assertEquals("Estabelecimento não encontrado", exception.getMessage());
        verify(estabelecimentoRepository, times(1)).buscaPorId(validUUID);
    }

    @Test
    void consultaEstabelecimentoPorId_ComIdInvalido_DeveLancarIllegalArgumentException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> consultaUsecase.consultaEstabelecimentoPorId(invalidUUIDString)
        );

        assertEquals("ID informada não é válida", exception.getMessage());
        verify(estabelecimentoRepository, never()).buscaPorId(any());
    }

    // Testes para consultaProfissionalPorId
    @Test
    void consultaProfissionalPorId_ComIdValido_DeveRetornarProfissional() {
        // Arrange
        when(profissionalRepository.buscaPorId(validUUID))
                .thenReturn(Optional.of(profissionalMock));

        // Act
        Profissional resultado = consultaUsecase.consultaProfissionalPorId(validUUIDString);

        // Assert
        assertNotNull(resultado);
        assertEquals(profissionalMock, resultado);
        verify(profissionalRepository, times(1)).buscaPorId(validUUID);
    }

    @Test
    void consultaProfissionalPorId_ComIdInexistente_DeveLancarEntityNotFoundException() {
        // Arrange
        when(profissionalRepository.buscaPorId(validUUID))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> consultaUsecase.consultaProfissionalPorId(validUUIDString)
        );

        assertEquals("Profissional não encontrado", exception.getMessage());
        verify(profissionalRepository, times(1)).buscaPorId(validUUID);
    }

    @Test
    void consultaProfissionalPorId_ComIdInvalido_DeveLancarIllegalArgumentException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> consultaUsecase.consultaProfissionalPorId(invalidUUIDString)
        );

        assertEquals("ID informada não é válida", exception.getMessage());
        verify(profissionalRepository, never()).buscaPorId(any());
    }


    // Testes para consultarAgendamentosPorCliente
    @Test
    void consultarAgendamentosPorCliente_ComClienteExistente_DeveRetornarListaAgendamentos() {
        // Arrange
        when(agendamentoRepository.listarPorCliente(validUUID))
                .thenReturn(agendamentosMock);

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorCliente(validUUID);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(agendamentosMock, resultado);
        verify(agendamentoRepository, times(1)).listarPorCliente(validUUID);
    }

    @Test
    void consultarAgendamentosPorCliente_ComClienteSemAgendamentos_DeveRetornarListaVazia() {
        // Arrange
        when(agendamentoRepository.listarPorCliente(validUUID))
                .thenReturn(Collections.emptyList());

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorCliente(validUUID);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(agendamentoRepository, times(1)).listarPorCliente(validUUID);
    }

    // Testes para consultarAgendamentosPorProfissional
    @Test
    void consultarAgendamentosPorProfissional_ComProfissionalExistente_DeveRetornarListaAgendamentos() {
        // Arrange
        when(agendamentoRepository.listarPorProfissional(validUUID))
                .thenReturn(agendamentosMock);

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorProfissional(validUUID);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(agendamentosMock, resultado);
        verify(agendamentoRepository, times(1)).listarPorProfissional(validUUID);
    }

    @Test
    void consultarAgendamentosPorProfissional_ComProfissionalSemAgendamentos_DeveRetornarListaVazia() {
        // Arrange
        when(agendamentoRepository.listarPorProfissional(validUUID))
                .thenReturn(Collections.emptyList());

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorProfissional(validUUID);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(agendamentoRepository, times(1)).listarPorProfissional(validUUID);
    }

    // Testes para consultarAgendamentosPorStatus
    @Test
    void consultarAgendamentosPorStatus_ComStatusExistente_DeveRetornarListaAgendamentos() {
        // Arrange
        StatusAgendamento status = StatusAgendamento.AGENDADO; // Assumindo que existe este enum
        when(agendamentoRepository.listarPorStatus(status))
                .thenReturn(agendamentosMock);

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorStatus(status);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(agendamentosMock, resultado);
        verify(agendamentoRepository, times(1)).listarPorStatus(status);
    }

    @Test
    void consultarAgendamentosPorStatus_ComStatusSemAgendamentos_DeveRetornarListaVazia() {
        // Arrange
        StatusAgendamento status = StatusAgendamento.CANCELADO; // Assumindo que existe este enum
        when(agendamentoRepository.listarPorStatus(status))
                .thenReturn(Collections.emptyList());

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorStatus(status);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(agendamentoRepository, times(1)).listarPorStatus(status);
    }

    // Testes para consultarAgendamentosPorClienteEStatus
    @Test
    void consultarAgendamentosPorClienteEStatus_ComParametrosValidos_DeveRetornarListaAgendamentos() {
        // Arrange
        StatusAgendamento status = StatusAgendamento.AGENDADO;
        when(agendamentoRepository.listarPorClienteEStatus(validUUID, status))
                .thenReturn(agendamentosMock);

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorClienteEStatus(validUUID, status);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(agendamentosMock, resultado);
        verify(agendamentoRepository, times(1)).listarPorClienteEStatus(validUUID, status);
    }

    @Test
    void consultarAgendamentosPorClienteEStatus_SemResultados_DeveRetornarListaVazia() {
        // Arrange
        StatusAgendamento status = StatusAgendamento.CANCELADO;
        when(agendamentoRepository.listarPorClienteEStatus(validUUID, status))
                .thenReturn(Collections.emptyList());

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorClienteEStatus(validUUID, status);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(agendamentoRepository, times(1)).listarPorClienteEStatus(validUUID, status);
    }

    // Testes para consultarAgendamentosPorProfissionalEStatus
    @Test
    void consultarAgendamentosPorProfissionalEStatus_ComParametrosValidos_DeveRetornarListaAgendamentos() {
        // Arrange
        StatusAgendamento status = StatusAgendamento.AGENDADO;
        when(agendamentoRepository.listarPorProfissionalEStatus(validUUID, status))
                .thenReturn(agendamentosMock);

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorProfissionalEStatus(validUUID, status);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(agendamentosMock, resultado);
        verify(agendamentoRepository, times(1)).listarPorProfissionalEStatus(validUUID, status);
    }

    @Test
    void consultarAgendamentosPorProfissionalEStatus_SemResultados_DeveRetornarListaVazia() {
        // Arrange
        StatusAgendamento status = StatusAgendamento.CANCELADO;
        when(agendamentoRepository.listarPorProfissionalEStatus(validUUID, status))
                .thenReturn(Collections.emptyList());

        // Act
        List<Agendamento> resultado = consultaUsecase.consultarAgendamentosPorProfissionalEStatus(validUUID, status);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(agendamentoRepository, times(1)).listarPorProfissionalEStatus(validUUID, status);
    }
}