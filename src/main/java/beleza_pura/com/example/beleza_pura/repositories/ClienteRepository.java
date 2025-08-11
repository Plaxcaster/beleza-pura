package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Cliente;
import beleza_pura.com.example.beleza_pura.repositories.jpa.ClienteJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.ClienteJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClienteRepository {

    private final ClienteJpaRepository repository;

    public ClienteRepository(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    public Cliente cadastrar(Cliente cliente) {
        ClienteJpaEntity clienteTable = new ClienteJpaEntity(cliente);
        clienteTable.setId(UUID.randomUUID());
        clienteTable = repository.save(clienteTable);

        return clienteTable.toClienteEntidade();
    }

    public Optional<Cliente> buscaPorId(UUID idCliente) {
        return repository.findById(idCliente)
                .map(ClienteJpaEntity::toClienteEntidade);
    }

}